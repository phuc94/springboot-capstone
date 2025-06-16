package com.cybersoft.capstone.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.cybersoft.capstone.entity.enums.CartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity(name = "carts")
@Data
public class Carts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Users user;

    @OneToMany(mappedBy = "carts", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    public void addItemToCart(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}
