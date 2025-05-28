package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.CouponTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponTypeRepository extends JpaRepository<CouponTypes, Integer> {
}
