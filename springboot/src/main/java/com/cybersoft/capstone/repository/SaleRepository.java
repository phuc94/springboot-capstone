package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sales, Integer> {
}
