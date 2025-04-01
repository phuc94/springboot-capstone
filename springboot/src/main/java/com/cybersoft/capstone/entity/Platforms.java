package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Platforms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private String buy_guide;
    private String activate_guide;

    @OneToMany(mappedBy = "platform")
    private List<PlatformSale> platformSales;
}
