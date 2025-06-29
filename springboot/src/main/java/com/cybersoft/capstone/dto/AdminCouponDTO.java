package com.cybersoft.capstone.dto;

import java.sql.Timestamp;

import com.cybersoft.capstone.entity.enums.CouponStatus;
import com.cybersoft.capstone.entity.enums.CouponUnit;

import lombok.Data;

@Data
public class AdminCouponDTO {
    private Integer id;
    private String code;
    private Integer couponTypeId;
    private CouponUnit couponUnit;
    private String description;
    private Integer discountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private Timestamp startDate;
    private Timestamp endDate;
    private CouponStatus status;
}
