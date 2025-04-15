package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.CouponDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CouponService {
    public List<CouponDTO> getAllCoupons();
    public CouponDTO getCouponById(int id);
    public CouponDTO createCoupon(@Valid CouponDTO couponDTO);
    public CouponDTO updateCoupon(int id,CouponDTO couponDTO);
    public void deleteCouponById(int id);
}
