package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class GameCardDTO {
    private int id;
    private String title;
    private int price;
    private int stock;
    private int sale;
    private int avgRating;
}

