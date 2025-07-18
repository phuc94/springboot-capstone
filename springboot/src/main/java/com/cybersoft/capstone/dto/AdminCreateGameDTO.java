package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminCreateGameDTO {
    private int id;
    private String title;
    private int price;
    private int stock;
    private int avgRating;
    private String media;
    private int platformId;
    private String description;
    private int saleId;
    private Timestamp deleted_on;
}
