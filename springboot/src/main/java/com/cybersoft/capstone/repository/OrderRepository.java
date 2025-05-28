package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByDeletedOnIsNull();
    Optional<Orders> findByIdAndDeletedOnIsNull(Integer id);
}
