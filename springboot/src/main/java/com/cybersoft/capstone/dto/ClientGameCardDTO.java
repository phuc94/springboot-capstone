package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class ClientGameCardDTO {
    private int id;
    private String title;
    private int salePrice;
    private int price;
    private int stock;
    private String img;
    private int avgRating;
    private SaleDTO sale;
}

