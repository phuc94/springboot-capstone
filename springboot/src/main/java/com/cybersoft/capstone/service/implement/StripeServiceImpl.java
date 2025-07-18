package com.cybersoft.capstone.service.implement;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.AdminCouponDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.GameItemDTO;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Coupons;
import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.OrderCoupon;
import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.entity.Orders;
import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;
import com.cybersoft.capstone.entity.enums.SaleStatus;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.request.CheckoutRequest;
import com.cybersoft.capstone.payload.response.StripeResponse;
import com.cybersoft.capstone.service.interfaces.CartItemService;
import com.cybersoft.capstone.service.interfaces.CartService;
import com.cybersoft.capstone.service.interfaces.ClientGameService;
import com.cybersoft.capstone.service.interfaces.CouponService;
import com.cybersoft.capstone.service.interfaces.GameKeyService;
import com.cybersoft.capstone.service.interfaces.OrderCouponService;
import com.cybersoft.capstone.service.interfaces.OrderItemService;
import com.cybersoft.capstone.service.interfaces.OrderService;
import com.cybersoft.capstone.service.interfaces.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Coupon;
import com.stripe.model.checkout.Session;
import com.stripe.param.CouponCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CartItemService cartItemService;
    private final GameKeyService gameKeyService;
    private final ClientGameService clientGameService;
    private final CartService cartService;
    private final CouponService couponService;
    private final OrderCouponService orderCouponService;

    public StripeServiceImpl(
        CartItemService cartItemService,
        OrderService orderService,
        OrderItemService orderItemService,
        GameKeyService gameKeyService,
        ClientGameService clientGameService,
        CartService cartService,
        CouponService couponService,
        OrderCouponService orderCouponService
    ) {
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.gameKeyService = gameKeyService;
        this.clientGameService = clientGameService;
        this.cartService = cartService;
        this.couponService = couponService;
        this.orderCouponService = orderCouponService;
    }

    @Override
    public StripeResponse checkout(int cartId, int userId, CheckoutRequest req) {
        Stripe.apiKey = secretKey;
        CartDetailDTO cartDetailDTO = cartService.getCartDetailByCartId(cartId);
        AdminCouponDTO adminCouponDTO = new AdminCouponDTO();
        Coupon stripeCoupon = null;

        if (!req.getCode().isEmpty()) {
            adminCouponDTO = couponService.getCouponByCode(req.getCode());
            if (adminCouponDTO.getUsedCount() >= adminCouponDTO.getUsageLimit()) {
                throw new NotFoundException("Coupon expired or reach limit usage.");
            }
            cartDetailDTO = couponService.applyCoupon(req.getCode(), cartDetailDTO);
            CouponCreateParams params = buildCouponCreateParams(adminCouponDTO);
            try {
                stripeCoupon = Coupon.create(params);
            } catch (StripeException ex) {
                throw new NotFoundException("Coupon expired or reach limit usage.");
            }
        }

        List<CartItem> cartItems = cartItemService.findByCartsId(cartId);
        List<SessionCreateParams.LineItem> lineItems = toLineItems(cartItems);
        SessionCreateParams params = buildSessionCrateParams(lineItems, stripeCoupon);
        System.out.println(params.toString());
        Session session = null;
        try {
            session = Session.create(params);
        } 
        catch (StripeException ex) {
            System.out.println(ex.getMessage());
        }

        OrderDTO orderDTO = buildOrderDTO(cartDetailDTO, session, req, userId);
        OrderDTO savedOrderDTO = orderService.save(orderDTO);

        // transfer cartItems into orderItems
        List<OrderItem> orderItems = toOrderItem(cartItems, savedOrderDTO);
        orderItemService.saveAll(orderItems);
        // delete all cartItems
        cartItemService.deleteAll(cartItems);

        // check if there is coupon code
        if (!req.getCode().isEmpty()) {

            // find coupon and transfer to order_coupon
            Orders savedOrder = new Orders();
            savedOrder.setId(savedOrderDTO.getId());
            Coupons coupon = new Coupons();
            coupon.setId(adminCouponDTO.getId());

            OrderCoupon orderCoupon = new OrderCoupon();
            orderCoupon.setOrder(savedOrder);
            orderCoupon.setCoupon(coupon);
            orderCouponService.save(orderCoupon);
            // Increase coupon used count
            adminCouponDTO.setUsedCount(adminCouponDTO.getUsedCount() + 1);
            couponService.updateCoupon(adminCouponDTO);
        }

        return buildStripeResponse(session);
    }

    @Override
    @Transactional
    public OrderDTO fulfillCheckout(String sessionId, int userId, int cartId) {
        OrderDTO orderDTO = orderService.findOrderBySessionId(sessionId);
        try {
          orderDTO.setPaymentStatus(PaymentMethodStatus.COMPLETED);
          orderDTO.setOrderStatus(OrderStatus.COMPLETED);
          orderService.save(orderDTO);

          // find and assign enough game_key to orderItem
          List<OrderItem> orderItems = orderItemService.findByOrderId(orderDTO.getId());
          orderItems.stream().forEach(orderItem -> {
              List<GameKey> gameKeys = gameKeyService.findTopNByGamesIdAndActivatedIsFalse(
                  orderItem.getGame().getId(), orderItem.getQuantity()
              );
              List<GameKey> activatedGameKeys = gameKeys.stream().map(gameKey -> {
                  gameKey.setOrderItem(orderItem);
                  gameKey.setActivated(true);
                  return gameKey;
              }).collect(Collectors.toList());
              gameKeyService.saveAll(activatedGameKeys);
              // update game's stock
              Games game = orderItem.getGame();
              game.setStock(game.getStock() - orderItem.getQuantity());
              clientGameService.save(game);
          });
          
        } catch (Error e) {
            System.out.println(e.getMessage());
        }

        return orderDTO;
    }

    private List<OrderItem> toOrderItem(List<CartItem> cartItems, OrderDTO orderDTO) {
        return cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setGame(cartItem.getGames());
                    Orders order = new Orders();
                    order.setId(orderDTO.getId());
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    int unitPrice = cartItem.getGames().getPrice();
                    Sales sale = cartItem.getGames().getSale();
                    if (sale != null && sale.getStatus().equals(SaleStatus.ACTIVE)) {
                        unitPrice = unitPrice * (100 - sale.getAmount()) / 100;
                    }
                    orderItem.setUnitPrice(unitPrice);
                    orderItem.setTotalPrice(unitPrice * orderItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    private List<SessionCreateParams.LineItem> toLineItems(List<CartItem> cartItems) {
        return cartItems.stream().map(cartItem -> this.toLineItem(cartItem))
                .collect(Collectors.toList());
    }

    private SessionCreateParams.LineItem toLineItem(CartItem cartItem) {
        GameItemDTO gameItemDTO = new GameItemDTO(cartItem);
        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
                .setName(gameItemDTO.getTitle()).build();

        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("VND")
                .setUnitAmount(Long.valueOf(gameItemDTO.getPrice()))
                .setProductData(productData)
                .build();

        return SessionCreateParams.LineItem.builder()
                .setQuantity(Long.valueOf(gameItemDTO.getQuantity()))
                .setPriceData(priceData)
                .build();
    }


    private SessionCreateParams buildSessionCrateParams(List<SessionCreateParams.LineItem> lineItems, Coupon stripeCoupon) {
        SessionCreateParams.Discount discount = null;
        if (stripeCoupon != null) {
          discount = SessionCreateParams.Discount.builder().setCoupon(stripeCoupon.getId()).build();
          return SessionCreateParams.builder()
                  .setMode(SessionCreateParams.Mode.PAYMENT)
                  .setSuccessUrl("http://phucserver:3000/payment/success")
                  .setCancelUrl("http://phucserver:3000/")
                  .addDiscount(discount)
                  .addAllLineItem(lineItems)
                  .build();
        }
        return SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://phucserver:3000/payment/success")
                .setCancelUrl("http://phucserver:3000/")
                .addAllLineItem(lineItems)
                .build();
    }

    private OrderDTO buildOrderDTO(CartDetailDTO cartDetailDTO, Session session, CheckoutRequest req, int userId) {
        OrderDTO orderDTO = OrderDTO.builder()
                .paymentMethodId(1)
                .paymentStatus(PaymentMethodStatus.PENDING)
                .orderStatus(OrderStatus.PENDING)
                .userId(userId)
                .url(session.getUrl())
                .email(req.getEmail())
                .name(req.getName())
                .phone(req.getPhone())
                .note(req.getNote())
                .originalAmount(cartDetailDTO.getOriginalPrice())
                .subTotalAmount(cartDetailDTO.getSubTotalAmount())
                .totalAmount(cartDetailDTO.getTotalAmount())
                .sessionId(session.getId())
                .build();
        if (!req.getCode().isEmpty()) {
            orderDTO.setDiscountAmount(cartDetailDTO.getDiscountAmount());
        }
        return orderDTO;
    }

    private StripeResponse buildStripeResponse(Session session) {
        return StripeResponse.builder()
                .status("PENDING")
                .message("Payment session created.")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
    private CouponCreateParams buildCouponCreateParams(AdminCouponDTO adminCouponDTO){
        CouponCreateParams params = null;
        switch (adminCouponDTO.getCouponUnit().toString()) {
          case "PERCENTAGE":
            params = CouponCreateParams.builder()
              .setPercentOff(new BigDecimal(adminCouponDTO.getDiscountAmount()))
              .setDuration(CouponCreateParams.Duration.ONCE)
              .build();
            break;
          case "FIXED":
            params = CouponCreateParams.builder()
              .setAmountOff(Long.valueOf(adminCouponDTO.getDiscountAmount()))
              .setDuration(CouponCreateParams.Duration.ONCE)
              .setCurrency("VND")
              .build();
            break;
          default:
            break;
        }
        return params;
    }

    // // TODO: 
    // // https://docs.stripe.com/checkout/fulfillment?payment-ui=stripe-hosted&lang=java
    // @Override
    // public void fulfillCheckout(String sessionId) {
    //     Stripe.apiKey = secretKey;
    //
    //     // TODO: Make this function safe to run multiple times,
    //     // even concurrently, with the same session ID
    //
    //     // TODO: Make sure fulfillment hasn't already been
    //     // performed for this Checkout Session
    //
    //     // Retrieve the Checkout Session from the API with line_items expanded
    //     SessionRetrieveParams params = SessionRetrieveParams.builder()
    //         .addExpand("line_items")
    //         .build();
    //
    //     Session checkoutSession = null;
    //     try {
    //         checkoutSession = Session.retrieve(sessionId, params, null);
    //     } catch (StripeException e) {
    //         System.out.println(e.getMessage());
    //     }
    //
    //     // Check the Checkout Session's payment_status property
    //     // to determine if fulfillment should be performed
    //     if (!checkoutSession.getPaymentStatus().equals("unpaid")) {
    //       // TODO: Perform fulfillment of the line items
    //
    //       // TODO: Record/save fulfillment status for this
    //       // Checkout Session
    //     }
    // }
}

