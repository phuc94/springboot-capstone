package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private int rating;
    private boolean recommend;
    private String comment;
}
