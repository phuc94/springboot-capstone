package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.OrderItem;

import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    public List<OrderItem> saveAll(List<OrderItem> orderItems);
}


