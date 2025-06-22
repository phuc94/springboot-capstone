package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByDeletedOnIsNull();
    List<Orders> findByUsersIdAndOrderStatusAndDeletedOnIsNull(int userId, String orderStatus);
    Optional<Orders> findByIdAndDeletedOnIsNull(Integer id);
    Optional<Orders> findBySessionIdAndDeletedOnIsNull(String sessionId);
    Optional<Orders> findByIdAndUsersId(int id, int userId);
    Boolean existsByUsersIdAndSessionId(int userId, String sessionId);
    Boolean existsByIdAndUsersId(int id, int userId);
}
