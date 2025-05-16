package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminGameDTO {
    private int id;
    private String title;
    private int price;
    private int stock;
    private Timestamp releaseDate;
    private int platformId;
    private int descriptionId;
    private String description;
}
