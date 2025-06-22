package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.OrderDTO;

import org.springframework.stereotype.Service;

@Service
public interface ClientOrderService {
    public List<OrderDTO> getUserOrder(int userId, String orderStatus);
    public OrderDTO getOrderById(int orderId, int userId);
    public void cancelOrderById(int orderId, int userId);
}
