package com.cybersoft.capstone.payload.response;

import com.cybersoft.capstone.dto.AdminDTO;

import lombok.Data;

@Data
public class AdminAuthResponse {
    private AdminDTO admin;
    private String token;
}
