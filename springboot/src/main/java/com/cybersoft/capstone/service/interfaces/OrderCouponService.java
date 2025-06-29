package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.OrderCoupon;

import org.springframework.stereotype.Service;

@Service
public interface OrderCouponService {
    public OrderCoupon save(OrderCoupon orderCoupon);
}
