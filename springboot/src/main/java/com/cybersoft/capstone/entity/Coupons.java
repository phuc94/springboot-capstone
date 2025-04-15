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
    private Integer id;
    @Column(nullable = false)
    private String code;
    private Double discountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private Timestamp startDate;
    private Timestamp endDate;
    @Column(nullable = false)
    private CouponStatus status;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id", referencedColumnName = "id")
    private CouponTypes couponType;
}
