package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.Roles;

import lombok.Data;

@Data
public class AdminDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private Roles role;
}

