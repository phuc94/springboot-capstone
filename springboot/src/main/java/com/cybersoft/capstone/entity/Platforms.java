package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name="parent_id", referencedColumnName = "id")
    private Platforms parent;

//    @OneToMany(mappedBy = "parent_id")
//    private List<Platforms> children = new ArrayList<>();

//    @OneToMany(mappedBy = "platform")
//    private List<PlatformSale> platformSales;
}
