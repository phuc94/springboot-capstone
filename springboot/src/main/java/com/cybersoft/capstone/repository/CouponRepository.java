package com.cybersoft.capstone.repository;

import java.util.Optional;

import com.cybersoft.capstone.entity.Coupons;
import com.cybersoft.capstone.entity.enums.CouponStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupons, Integer> {
    Optional<Coupons> findByCodeAndStatus(String code, CouponStatus status);
}
