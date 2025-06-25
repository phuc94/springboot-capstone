package com.cybersoft.capstone.dto;

import java.sql.Timestamp;
import java.util.List;

import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private int id;
    private int paymentMethodId;
    private String sessionId;
    private String url;
    private String email ;
    private String name ;
    private String phone ;
    private String note ;
    private PaymentMethodStatus paymentMethodStatus;
    private OrderStatus orderStatus;
    private PaymentMethodStatus paymentStatus;
    private int originalAmount;
    private int discountAmount;
    private int totalAmount;
    private int userId;
    private Timestamp deletedOn;
    private List<OrderItemDTO> orderItems;
}
