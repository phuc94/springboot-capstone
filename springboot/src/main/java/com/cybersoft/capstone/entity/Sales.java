package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.SaleStatus;
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

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private SaleStatus status;

//    @OneToMany(mappedBy = "sale")
//    private List<PlatformSale> platformSales;
}
