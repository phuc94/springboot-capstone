package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.CartStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity(name = "carts")
@Data
public class Carts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @OneToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Guests guest;


    @OneToMany(mappedBy = "carts")
    private List<CartItem> cartItems;
}
