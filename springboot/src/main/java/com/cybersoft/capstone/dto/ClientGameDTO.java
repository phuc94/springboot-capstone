package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class ClientGameDTO {
    private int id;
    private String title;
    private int price;
    private int salePrice;
    private int stock;
    private int avgRating;
    private SaleDTO sale;
}
