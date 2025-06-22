package com.cybersoft.capstone.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.dto.OrderItemDTO;
import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.entity.Orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "userId", target = "users.id")
    @Mapping(source = "paymentMethodId", target = "paymentMethod.id")
    Orders toOrder(OrderDTO orderDTO);

    @Mapping(source = "users.id", target = "userId")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    @Mapping(expression = "java(extractOrderItems(orders.getOrderItems()))", target = "orderItems")
    OrderDTO toOrderDTO(Orders orders);

    default List<OrderItemDTO> extractOrderItems(List<OrderItem> orderItems) {
        if (orderItems == null) return new ArrayList<OrderItemDTO>();
        return orderItems.stream()
            .map(orderItem -> {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setPrice(orderItem.getUnitPrice());
                orderItemDTO.setQuantity(orderItem.getQuantity());
                if (orderItem.getGame() != null) {
                  orderItemDTO.setGameId(orderItem.getGame().getId());
                  orderItemDTO.setImg(orderItem.getGame().getMedias().get(0).getUrl());
                  orderItemDTO.setTitle(orderItem.getGame().getTitle());
                }
                return orderItemDTO;
            }).collect(Collectors.toList());
    }

}
