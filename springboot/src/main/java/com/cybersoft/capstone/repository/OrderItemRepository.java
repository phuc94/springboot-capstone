package com.cybersoft.capstone.repository;

import java.util.List;

import com.cybersoft.capstone.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    public List<OrderItem> findByOrderId(int orderId);
}
