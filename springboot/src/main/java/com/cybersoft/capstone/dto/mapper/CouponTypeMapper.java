package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.CouponTypeDTO;
import com.cybersoft.capstone.entity.CouponTypes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponTypeMapper {
    CouponTypes toCouponType(CouponTypeDTO couponTypeDTO);
    CouponTypeDTO toCouponTypeDTO(CouponTypes couponTypes);
}
