package com.cybersoft.capstone.dto;

import java.util.List;

import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.entity.Medias;

import lombok.Data;

@Data
public class ClientGameDTO {
    private int id;
    private String title;
    private int price;
    private int stock;
    private int avgRating;
    private int platformId;
    private GameDescription gameDescription;
    private List<Medias> medias;
    private List<ReviewDTO> reviews;
    private int sale;
}
