package com.cybersoft.capstone.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Entity(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles role;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Carts cart;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<WishlistItem> wishlistItems;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Orders> orders;

}
