package com.cybersoft.capstone.controller.admin;

import com.cybersoft.capstone.dto.CouponTypeDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.CouponTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/coupon-type")
public class CouponTypeController {

    private final CouponTypeService couponTypeService;

    public CouponTypeController(CouponTypeService couponTypeService) {
        this.couponTypeService = couponTypeService;
    }

    @GetMapping
    public BaseResponse<List<CouponTypeDTO>> getAllCouponTypes() {
        List<CouponTypeDTO> couponType = couponTypeService.getAllCouponTypes();
        BaseResponse<List<CouponTypeDTO>> response = new BaseResponse<>(200, "Lấy danh sách coupon type thành công");
        response.setData(couponType);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<CouponTypeDTO> getCouponTypesById(@PathVariable int id) {
        try {
            CouponTypeDTO couponType = couponTypeService.getCouponTypeById(id);
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(200, "Lấy coupon type theo id thành công");
            response.setData(couponType);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(404, "Lấy không thành công!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping
    public BaseResponse<CouponTypeDTO> createCouponType(@RequestBody CouponTypeDTO couponTypeDTO) {
        try {
            CouponTypeDTO couponType = couponTypeService.createCouponType(couponTypeDTO);
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(201, "Thêm coupon type thành công");
            response.setData(couponType);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(500, "Thêm không thành công!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping("/{id}")
    public BaseResponse<CouponTypeDTO> updateCouponType(@PathVariable int id, @RequestBody CouponTypeDTO couponTypeDTO) {
        try {
            CouponTypeDTO updateCouponType = couponTypeService.updateCouponType(id, couponTypeDTO);
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(200, "Update coupon type thành công");
            response.setData(updateCouponType);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<CouponTypeDTO> response = new BaseResponse<>(500, "Update không thành công!");
            response.setData(null);
            return response;
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCouponType(@PathVariable int id) {
        try {
            couponTypeService.deleteCouponTypeById(id);
            BaseResponse<Void> response = new BaseResponse<>(204, "Xóa coupon type thành công");
            response.setData(null);
            return response;
        } catch (Exception ex) {
            BaseResponse<Void> response = new BaseResponse<>(500, "Xóa coupon type không thành công");
            response.setData(null);
            return response;
        }
    }

}
