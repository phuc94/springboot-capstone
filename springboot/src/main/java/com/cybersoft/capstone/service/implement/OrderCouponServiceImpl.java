package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.OrderCoupon;
import com.cybersoft.capstone.repository.OrderCouponRepository;
import com.cybersoft.capstone.service.interfaces.OrderCouponService;

import org.springframework.stereotype.Service;

@Service
public class OrderCouponServiceImpl implements OrderCouponService {
    
    private OrderCouponRepository orderCouponRepository;

    public OrderCouponServiceImpl(OrderCouponRepository orderCouponRepository) {
        this.orderCouponRepository = orderCouponRepository;
    }

    @Override
    public OrderCoupon save(OrderCoupon orderCoupon) {
        return orderCouponRepository.save(orderCoupon);
    }

}
