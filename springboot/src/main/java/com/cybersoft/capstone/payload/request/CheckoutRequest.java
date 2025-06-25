package com.cybersoft.capstone.payload.request;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CheckoutRequest {
    @NotNull
    private String email;
    private String name;
    @NotNull
    private String phone;
    private String note;
}

