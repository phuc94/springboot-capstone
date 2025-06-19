package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private int amount;
    private int quantity;
    private String name;
    private String currency;
}

