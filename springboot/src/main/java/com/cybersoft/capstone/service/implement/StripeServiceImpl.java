package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.GameItemDTO;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;
import com.cybersoft.capstone.payload.response.StripeResponse;
import com.cybersoft.capstone.service.interfaces.CartItemService;
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

    private CartItemService cartItemService;

    public StripeServiceImpl(CartItemService cartItemService, OrderService orderService) {
        this.cartItemService = cartItemService;
        this.orderService = orderService;
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

    @Override
    @Transactional
    public void fulfillCheckout(String sessionId) {
        try {
          OrderDTO orderDTO = orderService.findOrderBySessionId(sessionId);
          orderDTO.setPaymentStatus(PaymentMethodStatus.COMPLETED);
          orderDTO.setOrderStatus(OrderStatus.COMPLETED);
          orderService.save(orderDTO);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
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

