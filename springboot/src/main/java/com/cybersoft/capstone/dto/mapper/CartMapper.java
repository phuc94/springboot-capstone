package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.entity.Carts;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Carts toCarts(CartDTO cartDTO);
    CartDTO toCartDTO(Carts carts);
}
