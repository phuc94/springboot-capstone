package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.repository.OrderItemRepository;
import com.cybersoft.capstone.service.interfaces.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl (OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

}

