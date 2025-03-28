package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Sales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Timestamp startDate;
    private Timestamp endDate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private SaleStatus status;

    @OneToMany(mappedBy = "sales")
    private List<PublisherSale> publisherSale;
}
