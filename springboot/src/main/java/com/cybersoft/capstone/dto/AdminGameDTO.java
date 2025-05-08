package com.cybersoft.capstone.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminGameDTO {
    private int id;
    private String title;
    private Integer price;
    private boolean isDlc;
    private int keyCount;
    private Timestamp releaseDate;
    private int platformId;
    private int descriptionId;
}
