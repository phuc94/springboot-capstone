package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.dto.mapper.OrderMapper;
import com.cybersoft.capstone.entity.enums.OrderStatus;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.service.interfaces.ClientOrderService;

import org.springframework.stereotype.Service;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public ClientOrderServiceImpl (OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getUserOrder(int userId, String orderStatus) {
        return orderRepository.findByUsersIdAndOrderStatusAndDeletedOnIsNull(userId, orderStatus)
              .stream().map(orderMapper::toOrderDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int orderId, int userId) {
        return orderRepository.findByIdAndUsersId(orderId, userId)
              .map(orderMapper::toOrderDTO)
              .orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public void cancelOrderById(int orderId, int userId) {
        orderRepository.findByIdAndUsersId(orderId, userId).map(order -> {
                order.setOrderStatus(OrderStatus.CANCELLED.toString());
                return orderRepository.save(order);
            })
              .orElseThrow(() -> new NotFoundException("Order not found"));
    }

}

