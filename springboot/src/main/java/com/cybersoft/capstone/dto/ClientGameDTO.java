package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class ClientGameDTO {
    private int id;
    private String title;
    private int price;
    private int stock;
    private int platformId;
    private int descriptionId;
    private String description;
    private int saleId;
}
