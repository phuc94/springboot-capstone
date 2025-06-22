package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.OrderItemDTO;
import com.cybersoft.capstone.entity.OrderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toOrderItem(OrderItemDTO orderItemDTO);

    @Mapping(source = "game.id", target = "gameId")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
}
