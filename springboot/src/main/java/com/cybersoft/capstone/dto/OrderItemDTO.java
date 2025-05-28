package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int quantity;
    private double price;
    private int orderId;
    private int gameId;
}
