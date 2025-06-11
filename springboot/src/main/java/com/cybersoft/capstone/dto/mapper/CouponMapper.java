package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminCouponDTO;
import com.cybersoft.capstone.entity.Coupons;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    @Mapping(source = "couponTypeId", target = "couponType.id")
    Coupons toCoupons(AdminCouponDTO couponDTO);

    @Mapping(source = "couponType.id", target = "couponTypeId")
    AdminCouponDTO toAdminCouponDTO(Coupons coupons);
}
