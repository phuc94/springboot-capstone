package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Coupons;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.CouponService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public BaseResponse<List<Coupons>> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public BaseResponse<Coupons> getCouponById(@Valid @PathVariable int id) {
        return couponService.getCouponById(id);
    }

    @PostMapping
    public BaseResponse<Coupons> createCoupon(@Valid @RequestBody Coupons coupon) {
        return couponService.createCoupon(coupon);
    }

    @PostMapping("/{id}")
    public BaseResponse<Coupons> updateCoupon(@Valid @PathVariable int id, @Valid @RequestBody Coupons coupons) {
        return couponService.updateCoupon(id, coupons);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCouponById(@Valid @PathVariable int id) {
        return couponService.deleteCouponById(id);
    }
}
