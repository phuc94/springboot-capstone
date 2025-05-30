package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.CouponTypes;

public interface CouponTypeService {
    public List<CouponTypes> getAllCouponTypes();
    public CouponTypes getCouponTypeById(int id);
    public CouponTypes createCouponType(@Valid CouponTypes couponType);
    public CouponTypes updateCouponType(int id, CouponTypes couponType);
    public void deleteCouponTypeById(int id);
}
