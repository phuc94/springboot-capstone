package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupons, Integer> { }
