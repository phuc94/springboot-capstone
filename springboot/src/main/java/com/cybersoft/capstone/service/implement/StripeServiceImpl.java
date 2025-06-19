package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.GameItemDTO;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.entity.Orders;
import com.cybersoft.capstone.entity.Sales;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;
import com.cybersoft.capstone.entity.enums.SaleStatus;
import com.cybersoft.capstone.payload.response.StripeResponse;
import com.cybersoft.capstone.service.interfaces.CartItemService;
import com.cybersoft.capstone.service.interfaces.ClientGameService;
import com.cybersoft.capstone.service.interfaces.GameKeyService;
import com.cybersoft.capstone.service.interfaces.OrderItemService;
import com.cybersoft.capstone.service.interfaces.OrderService;
import com.cybersoft.capstone.service.interfaces.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secretKey}")
    private String secretKey;

    private OrderService orderService;
    private OrderItemService orderItemService;
    private CartItemService cartItemService;
    private GameKeyService gameKeyService;
    private ClientGameService clientGameService;

    public StripeServiceImpl(
        CartItemService cartItemService,
        OrderService orderService,
        OrderItemService orderItemService,
        GameKeyService gameKeyService,
        ClientGameService clientGameService
    ) {
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.gameKeyService = gameKeyService;
        this.clientGameService = clientGameService;
    }

    @Override
    public StripeResponse checkout(int cartId, int userId) {
        Stripe.apiKey = secretKey;
        List<CartItem> cartItems = cartItemService.findByCartsId(cartId);

        List<SessionCreateParams.LineItem> lineItems = toLineItems(cartItems);

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://phucserver:3000/payment/success")
                .setCancelUrl("http://phucserver:3000/cancel")
                .addAllLineItem(lineItems)
                .build();

        Session session = null;
        try {
            session = Session.create(params);
        } 
        catch (StripeException ex) {
            System.out.println(ex.getMessage());
        }

        OrderDTO orderDTO = OrderDTO.builder()
                .paymentMethodId(1)
                .paymentStatus(PaymentMethodStatus.PENDING)
                .orderStatus(OrderStatus.PENDING)
                .userId(userId)
                .totalAmount(900)
                .sessionId(session.getId())
                .build();

        orderService.save(orderDTO);

        StripeResponse stripeResponse = StripeResponse.builder()
                .status("PENDING")
                .message("Payment session created.")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();

        return stripeResponse;
    }

    @Override
    @Transactional
    public void fulfillCheckout(String sessionId, int userId, int cartId) {
        try {
          OrderDTO orderDTO = orderService.findOrderBySessionId(sessionId);
          orderDTO.setPaymentStatus(PaymentMethodStatus.COMPLETED);
          orderDTO.setOrderStatus(OrderStatus.COMPLETED);
          orderService.save(orderDTO);

          // transfer cartItems into orderItems
          List<CartItem> cartItems = cartItemService.findByCartsId(cartId);
          List<OrderItem> orderItems = toOrderItem(cartItems, orderDTO);
          orderItemService.saveAll(orderItems);
          // delete all cartItems
          cartItemService.deleteAll(cartItems);
          // find and assign enough game_key to orderItem
          orderItems.stream().forEach(orderItem -> {
              List<GameKey> gameKeys = gameKeyService.findTopNByGamesIdAndActivatedIsFalse(
                  orderItem.getGame().getId(), orderItem.getQuantity()
              );
              List<GameKey> activatedGameKeys = gameKeys.stream().map(gameKey -> {
                  System.out.println(gameKey.isActivated());
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

