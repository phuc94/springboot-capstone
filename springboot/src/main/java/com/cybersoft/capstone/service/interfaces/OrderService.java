package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.OrderDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> getAllOrders();
    public OrderDTO getOrderById(int id);
    public OrderDTO createOrder(@Valid OrderDTO orderDTO);
    public void softDeleteOrderById(int id);
}
