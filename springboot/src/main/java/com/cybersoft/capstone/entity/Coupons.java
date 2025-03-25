package com.cybersoft.capstone.entity;

import com.cybersoft.capstone.entity.enums.CouponStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class Coupons implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String code;
    private double discount_amount;
    private int usage_limit;
    private int used_count;
    private Timestamp start_date;
    private Timestamp end_date;
    @Column(nullable = false)
    private CouponStatus status;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id", referencedColumnName = "id")
    private CouponTypes couponType;
}
