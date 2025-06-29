package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.AdminCouponDTO;
import com.cybersoft.capstone.dto.CartDetailCouponDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;

public interface CouponService {
    public List<AdminCouponDTO> getAllCoupons();
    public AdminCouponDTO getCouponById(int id);
    public AdminCouponDTO getCouponByCode(String code);
    public CartDetailCouponDTO applyCoupon(String code, CartDetailDTO cart);
    public AdminCouponDTO createCoupon(@Valid AdminCouponDTO couponDTO);
    public AdminCouponDTO updateCoupon(AdminCouponDTO couponDTO);
    public void deleteCouponById(int id);
}
