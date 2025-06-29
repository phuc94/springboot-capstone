package com.cybersoft.capstone.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.cybersoft.capstone.entity.enums.CouponStatus;
import com.cybersoft.capstone.entity.enums.CouponUnit;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import lombok.Data;

@Entity
@Data
public class Coupons implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer discountAmount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private CouponUnit couponUnit;

    private Integer usageLimit;
    @Column(nullable = false)

    private Integer usedCount;
    private Timestamp startDate;

    @Column(nullable = false)
    private Timestamp endDate;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private CouponStatus status;

    @ManyToOne
    @JoinColumn(name = "coupon_type_id", referencedColumnName = "id")
    private CouponTypes couponType;

}
