package com.cybersoft.capstone.controller.client;

import java.util.List;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.OrderService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class ClientOrderController {

    private final OrderService orderService;

    public ClientOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public BaseResponse<List<OrderDTO>> getUserCompletedOrders(@AuthenticationPrincipal CustomUserDetails user) {
        List<OrderDTO> orders = orderService.getOrdersByUserIdAndStatus(user.getId(), OrderStatus.COMPLETED);
        return new OkResponse<>(orders);
    }
}
}
