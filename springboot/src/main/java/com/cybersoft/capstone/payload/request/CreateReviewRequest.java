package com.cybersoft.capstone.payload.request;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateReviewRequest {
    @NotNull(message = "gameId can't be null")
    private Integer gameId;
    @NotNull(message = "orderId can't be null")
    private Integer orderId;
    @NotNull(message = "rating can't be null")
    private Integer rating;
    private String comment;
}

