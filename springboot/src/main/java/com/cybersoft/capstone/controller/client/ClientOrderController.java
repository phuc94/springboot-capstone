package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.ClientOrderService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class ClientOrderController {

    private final ClientOrderService clientOrderService;

    public ClientOrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @GetMapping("/{orderId}")
    public BaseResponse<OrderDTO> getOrderById(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int orderId) {
        return new OkResponse<>(clientOrderService.getOrderById(orderId, user.getId()));
    }

    // TODO: merge 3 order status
    @GetMapping("/success")
    public BaseResponse<List<OrderDTO>> getUserOrderSuccess(@AuthenticationPrincipal CustomUserDetails user) {
        return new OkResponse<>(clientOrderService.getUserOrder(user.getId(), OrderStatus.COMPLETED.toString()));
    }

    @GetMapping("/pending")
    public BaseResponse<List<OrderDTO>> getUserOrderPending(@AuthenticationPrincipal CustomUserDetails user) {
        return new OkResponse<>(clientOrderService.getUserOrder(user.getId(), OrderStatus.PENDING.toString()));
    }

    @GetMapping("/canceled")
    public BaseResponse<List<OrderDTO>> getUserOrderCanceled(@AuthenticationPrincipal CustomUserDetails user) {
        return new OkResponse<>(clientOrderService.getUserOrder(user.getId(), OrderStatus.CANCELLED.toString()));
    }

    @DeleteMapping("/{orderId}")
    public BaseResponse<?> cancelOrderById(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int orderId) {
        clientOrderService.cancelOrderById(orderId, user.getId());
        return new AcceptedResponse<>();
    }

}


