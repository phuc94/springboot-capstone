package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.payload.response.StripeResponse;
import com.cybersoft.capstone.service.interfaces.OrderService;
import com.cybersoft.capstone.service.interfaces.StripeService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class ClientCheckoutController {

    StripeService stripeService;
    OrderService orderService;

    public ClientCheckoutController(StripeService stripeService, OrderService orderService) {
        this.stripeService = stripeService;
        this.orderService = orderService;
    }

    @PostMapping
    public BaseResponse<StripeResponse> checkout(@AuthenticationPrincipal CustomUserDetails user) {
        return new OkResponse<StripeResponse>(stripeService.checkout(user.getCart().getId(), user.getId()));
    }

    @PostMapping("/fullfill")
    public BaseResponse<?> fullfill(@AuthenticationPrincipal CustomUserDetails user, @RequestParam String sessionId) {
        if (orderService.checkUserOrderSessionId(user.getId(), sessionId)) {
            stripeService.fulfillCheckout(sessionId);
        } else {
            throw new NotFoundException("Order not found");
        }
        return new AcceptedResponse<>();
    }

}

