package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.enums.CouponUnit;

import lombok.Data;

@Data
public class ClientCouponDTO {
    private String code;
    private String description;
    private Integer discountAmount;
    private CouponUnit couponUnit;
}
