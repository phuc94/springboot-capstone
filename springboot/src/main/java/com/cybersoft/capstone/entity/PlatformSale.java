package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlatformSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="sale_id", referencedColumnName = "id", nullable = false)
    private Sales sale;

    @ManyToOne
    @JoinColumn(name="platform_id", referencedColumnName = "id", nullable = false)
    private Platforms platform;
}
