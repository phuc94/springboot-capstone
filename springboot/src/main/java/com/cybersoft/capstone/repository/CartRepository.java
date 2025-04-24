package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Carts, Integer> {
}
