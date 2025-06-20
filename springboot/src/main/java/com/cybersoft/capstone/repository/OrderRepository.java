package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.Orders;
import com.cybersoft.capstone.entity.enums.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByDeletedOnIsNull();
    Optional<Orders> findByIdAndDeletedOnIsNull(Integer id);
    Optional<Orders> findBySessionIdAndDeletedOnIsNull(String sessionId);
    Boolean existsByUsersIdAndSessionId(int userId, String sessionId);
    List<Orders> findByUsersIdAndOrderStatusAndDeletedOnIsNull(int userId, OrderStatus orderStatus);
}

