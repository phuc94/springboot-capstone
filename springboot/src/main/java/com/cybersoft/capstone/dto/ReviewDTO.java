package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewDTO {
    private int id;
    private int rating;
    private String comment;
    private UserDTO user;
    private Timestamp createdAt;
}
