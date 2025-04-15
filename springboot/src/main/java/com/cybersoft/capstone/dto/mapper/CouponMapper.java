package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.CouponDTO;
import com.cybersoft.capstone.entity.Coupons;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupons toCoupons(CouponDTO couponDTO);
    CouponDTO toCouponDTO(Coupons coupons);
}
