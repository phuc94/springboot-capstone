package com.cybersoft.capstone.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.cybersoft.capstone.entity.enums.CouponStatus;

import lombok.Data;

@Entity
@Data
public class Coupons implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String code;
    private Double discountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private Timestamp startDate;
    private Timestamp endDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private CouponTypes couponType;
}
