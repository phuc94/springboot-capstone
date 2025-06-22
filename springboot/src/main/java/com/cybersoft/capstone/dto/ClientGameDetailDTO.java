package com.cybersoft.capstone.dto;

import java.util.List;

import com.cybersoft.capstone.entity.Medias;

import lombok.Data;

@Data
public class ClientGameDetailDTO {
    private int id;
    private String title;
    private int salePrice;
    private int price;
    private int stock;
    private int avgRating;
    private int platformId;
    private GameDescriptionDTO gameDescription;
    private List<Medias> medias;
    private List<ReviewDTO> reviews;
    private SaleDTO sale;
}
