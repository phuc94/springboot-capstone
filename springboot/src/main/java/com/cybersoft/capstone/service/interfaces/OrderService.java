package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.OrderDTO;

public interface OrderService {
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(int id);
    public void softDeleteOrderById(int id);
    public OrderDTO createOrder(@Valid OrderDTO orderDTO);
    public OrderDTO findOrderBySessionId(String sessionId);
    public OrderDTO save(OrderDTO order);
    public Boolean checkUserOrderSessionId(int userId, String sessionId);
}
