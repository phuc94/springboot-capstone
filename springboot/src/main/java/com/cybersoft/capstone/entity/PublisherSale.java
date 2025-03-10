package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class PublisherSale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sales sales;

    @OneToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publishers publishers;
}
