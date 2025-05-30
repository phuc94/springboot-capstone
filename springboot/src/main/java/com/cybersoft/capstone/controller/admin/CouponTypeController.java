package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.CouponTypes;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.CouponTypeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/coupon_type")
public class CouponTypeController {

    private final CouponTypeService couponTypeService;

    public CouponTypeController(CouponTypeService couponTypeService) {
        this.couponTypeService = couponTypeService;
    }

    @GetMapping
    public BaseResponse<List<CouponTypes>> getAllCouponTypes() {
        List<CouponTypes> coupons = couponTypeService.getAllCouponTypes();
        BaseResponse<List<CouponTypes>> response = new BaseResponse<>(200, "Lấy danh sách coupon thành công");
        response.setData(coupons);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<CouponTypes> getCouponById(@Valid @PathVariable int id) {
        CouponTypes coupons = couponTypeService.getCouponTypeById(id);
        BaseResponse<CouponTypes> response = new BaseResponse<>(200, "Lấy id coupon thành công");
        response.setData(coupons);
        return response;
    }

    @PostMapping
    public BaseResponse<CouponTypes> createCoupon(@Valid @RequestBody CouponTypes couponType) {
        CouponTypes createdCoupon = couponTypeService.createCouponType(couponType);
        BaseResponse<CouponTypes> response = new BaseResponse<>(201, "Tạo coupon thành công");
        response.setData(createdCoupon);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<CouponTypes> updateCoupon(@Valid @PathVariable int id, @RequestBody CouponTypes couponType) {
        com.cybersoft.capstone.entity.CouponTypes updatedCoupon = couponTypeService.updateCouponType(id, couponType);
        BaseResponse<CouponTypes> response = new BaseResponse<>(200, "Cập nhật coupon thành công");
        response.setData(updatedCoupon);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCouponById(@Valid @PathVariable int id) {
        couponTypeService.deleteCouponTypeById(id);
        return new BaseResponse<>(200, "Xóa coupon thành công");
    }

}
