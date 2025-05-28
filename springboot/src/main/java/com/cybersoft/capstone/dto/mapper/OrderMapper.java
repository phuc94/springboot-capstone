package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Orders toOrder(OrderDTO orderDTO);

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    OrderDTO toOrderDTO(Orders orders);
}
