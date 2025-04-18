package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String name;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "user")
    private Carts cart;

    @OneToMany(mappedBy = "user")
    private List<WishlistItem> wishlistItems;
}
