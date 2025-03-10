package com.cybersoft.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class Sales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;
    private double amount;

    @OneToOne(mappedBy = "sales")
    private PublisherSale publisherSale;
}
