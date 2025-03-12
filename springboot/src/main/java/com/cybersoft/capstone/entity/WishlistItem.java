package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class WishlistItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Games game;
}
