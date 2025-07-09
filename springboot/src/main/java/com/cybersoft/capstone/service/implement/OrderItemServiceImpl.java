package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.exception.NotFoundException;
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

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
       return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public Boolean existsByOrderIdAndGameId(int orderId, int gameId) {
        return orderItemRepository.existsByOrderIdAndGameId(orderId, gameId);
    }

    @Override
    public OrderItem findByOrderIdAndGameId(int orderId, int gameId) {
        return orderItemRepository.findByOrderIdAndGameId(orderId, gameId)
            .orElseThrow(()-> new NotFoundException("Order not found!"));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}

