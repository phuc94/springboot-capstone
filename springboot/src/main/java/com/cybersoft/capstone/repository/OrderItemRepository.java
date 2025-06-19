package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> { }
