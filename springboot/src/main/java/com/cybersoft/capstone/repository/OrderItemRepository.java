package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    public List<OrderItem> findByOrderId(int orderId);
    public Optional<OrderItem> findByOrderIdAndGameId(int orderId, int gameId);
    public Boolean existsByOrderIdAndGameId(int orderId, int gameId);
}
