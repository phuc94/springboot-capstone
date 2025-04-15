package com.cybersoft.capstone.delegate.admin;

import com.cybersoft.capstone.api.AdminCouponApiDelegate;
import com.cybersoft.capstone.dto.CouponDTO;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.CouponService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminCouponApiDelegateImpl implements AdminCouponApiDelegate {

    private final CouponService couponService;

    public AdminCouponApiDelegateImpl(CouponService couponService) {
        this.couponService = couponService;
    }

    @Override
    public BaseResponse<CouponDTO> createAdminCoupon(CouponDTO couponDTO) {
        return new OkResponse<>(couponService.createCoupon(couponDTO));
    }

    @Override
    public BaseResponse<Object> deleteAdminCoupon(Integer id) {
        couponService.deleteCouponById(id);
        return new AcceptedResponse<>();
    }

    @Override
    public BaseResponse<List<CouponDTO>> getAllAdminCoupons() {
        return new OkResponse<>(couponService.getAllCoupons());
    }

    @Override
    public BaseResponse<CouponDTO> getAdminCouponById(Integer id) {
        return new OkResponse<>(couponService.getCouponById(id));
    }

    @Override
    public BaseResponse<CouponDTO> updateAdminCoupon(Integer id, CouponDTO couponDTO) {
        return new OkResponse<>(couponService.updateCoupon(id, couponDTO));
    }
}
