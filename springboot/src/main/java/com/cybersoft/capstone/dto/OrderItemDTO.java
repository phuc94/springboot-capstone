package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private int quantity;
    private double price;
    private int gameId;
    private String title;
    private String img;
}
