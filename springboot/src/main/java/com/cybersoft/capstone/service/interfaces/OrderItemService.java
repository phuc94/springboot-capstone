package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.OrderItem;

import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    public List<OrderItem> saveAll(List<OrderItem> orderItems);
    public OrderItem save(OrderItem orderItem);
    public List<OrderItem> findByOrderId(int orderId);
    public OrderItem findByOrderIdAndGameId(int orderId, int gameId);
    public Boolean existsByOrderIdAndGameId(int orderId, int gameId);
}


