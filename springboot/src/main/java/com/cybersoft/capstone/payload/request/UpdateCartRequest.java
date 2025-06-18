package com.cybersoft.capstone.payload.request;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCartRequest {
    @NotNull
    private Integer gameId;

    @NotNull
    private Integer quantity;
} 


