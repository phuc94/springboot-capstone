package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.CouponTypeDTO;
import com.cybersoft.capstone.entity.CouponTypes;
import jakarta.validation.Valid;

import java.util.List;

public interface CouponTypeService {
    public List<CouponTypeDTO> getAllCouponTypes();
    public CouponTypeDTO getCouponTypeById(int id);
    public CouponTypeDTO createCouponType(@Valid CouponTypeDTO couponTypeDTO);
    public CouponTypeDTO updateCouponType(int id, CouponTypeDTO couponTypeDTO);
    public void deleteCouponTypeById(int id);
}
