package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class GameItemDTO {
    private int gameId;
    private String title;
    private int price;
    private int quantity;
    private String img;
    private SaleDTO sale;
}

