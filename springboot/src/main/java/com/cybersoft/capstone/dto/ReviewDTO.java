package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewDTO {
    private int id;
    private int gameId;
    private int rating;
    private String comment;
    private SimpleUserDTO user;
    private Timestamp createdAt;
}
