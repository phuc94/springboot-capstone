package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.Carts;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String email;
    private String name;
    private Carts cart;
}
