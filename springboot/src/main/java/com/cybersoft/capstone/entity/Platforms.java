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

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name="parent_id", referencedColumnName = "id")
    private Platforms platform;

    @OneToMany(mappedBy = "platform")
    private List<PlatformSale> platformSales;
}
