package com.cybersoft.capstone.controller.client;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.request.CheckoutRequest;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.payload.response.StripeResponse;
import com.cybersoft.capstone.service.interfaces.OrderService;
import com.cybersoft.capstone.service.interfaces.StripeService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BaseResponse<StripeResponse> checkout(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody CheckoutRequest req) {
        return new OkResponse<StripeResponse>(stripeService.checkout(user.getCart().getId(), user.getId(), req));
    }

    @PostMapping("/fullfill")
    public BaseResponse<OrderDTO> fullfill(@AuthenticationPrincipal CustomUserDetails user, @RequestParam String sessionId) {
        if (orderService.checkUserOrderSessionId(user.getId(), sessionId)) {
            return new OkResponse<OrderDTO>(stripeService.fulfillCheckout(sessionId, user.getId(), user.getCart().getId()));
        } else {
            throw new NotFoundException("Order not found");
        }
    }

}

