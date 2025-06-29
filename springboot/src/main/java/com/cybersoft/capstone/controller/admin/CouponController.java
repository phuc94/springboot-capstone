package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.AdminCouponDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.CouponService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public BaseResponse<List<AdminCouponDTO>> getAllCoupons() {
        List<AdminCouponDTO> coupons = couponService.getAllCoupons();
        BaseResponse<List<AdminCouponDTO>> response = new BaseResponse<>(200, "Lấy danh sách coupon thành công");
        response.setData(coupons);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<AdminCouponDTO> getCouponById(@Valid @PathVariable int id) {
        AdminCouponDTO coupons = couponService.getCouponById(id);
        BaseResponse<AdminCouponDTO> response = new BaseResponse<>(200, "Lấy id coupon thành công");
        response.setData(coupons);
        return response;
    }

    @PostMapping
    public BaseResponse<AdminCouponDTO> createCoupon(@Valid @RequestBody AdminCouponDTO couponDTO) {
        AdminCouponDTO createdCoupon = couponService.createCoupon(couponDTO);
        BaseResponse<AdminCouponDTO> response = new BaseResponse<>(201, "Tạo coupon thành công");
        response.setData(createdCoupon);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<AdminCouponDTO> updateCoupon(@Valid @PathVariable int id, @RequestBody AdminCouponDTO couponDTO) {
        AdminCouponDTO updatedCoupon = couponService.updateCoupon(couponDTO);
        BaseResponse<AdminCouponDTO> response = new BaseResponse<>(200, "Cập nhật coupon thành công");
        response.setData(updatedCoupon);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCouponById(@Valid @PathVariable int id) {
        couponService.deleteCouponById(id);
        return new BaseResponse<>(200, "Xóa coupon thành công");
    }

}
