package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.entity.enums.PaymentMethodStatus;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrderDTO {
    private int id;
    private int paymentMethodId;
    private PaymentMethodStatus paymentMethodStatus;
    private OrderStatus orderStatus;
    private PaymentMethodStatus paymentStatus;
    private int originalAmount;
    private int discountedAmount;
    private int totalAmount;
    private int userId;
    private Timestamp deletedOn;
}
