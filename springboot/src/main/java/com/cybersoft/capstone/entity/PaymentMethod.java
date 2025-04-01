package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String account_name;
    @Column(nullable = false)
    private String account_number;
    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Orders> orders;
}
