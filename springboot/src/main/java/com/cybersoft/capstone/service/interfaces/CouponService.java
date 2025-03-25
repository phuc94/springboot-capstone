package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Coupons;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface CouponService {
    public BaseResponse<List<Coupons>> getAllCoupons();
    public BaseResponse<Coupons> getCouponById(int id);
    public BaseResponse<Coupons> createCoupon(Coupons coupon);
    public BaseResponse<Coupons> updateCoupon(int id, Coupons coupon);
    public BaseResponse<Void> deleteCouponById(int id);
}
