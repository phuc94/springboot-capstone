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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    private int sessionId;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @OneToMany(mappedBy = "carts")
    private List<CartItem> cartItems;
}
