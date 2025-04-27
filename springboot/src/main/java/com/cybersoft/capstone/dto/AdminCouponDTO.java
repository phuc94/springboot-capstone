package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.enums.CouponStatus;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdminCouponDTO {
    private String code;
    private Double discountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private Timestamp startDate;
    private Timestamp endDate;
    private CouponStatus status;
}
