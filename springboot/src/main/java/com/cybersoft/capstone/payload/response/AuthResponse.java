package com.cybersoft.capstone.payload.response;

import com.cybersoft.capstone.dto.UserDTO;

import lombok.Data;

@Data
public class AuthResponse {
    private UserDTO user;
    private String token;
}
