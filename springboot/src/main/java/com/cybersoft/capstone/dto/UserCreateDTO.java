package com.cybersoft.capstone.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String email;
    private String name;
    private String password;
}
