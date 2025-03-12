package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String address;

    @OneToOne(mappedBy = "users")
    private Carts carts;
}
