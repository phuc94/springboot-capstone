package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "cart_items")
@Data
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Carts carts;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Games games;
}
