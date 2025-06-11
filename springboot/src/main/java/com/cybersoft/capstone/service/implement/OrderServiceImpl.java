package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.dto.mapper.OrderMapper;
import com.cybersoft.capstone.entity.Orders;
import com.cybersoft.capstone.entity.PaymentMethod;
import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.OrderRepository;
import com.cybersoft.capstone.repository.PaymentMethodRepository;
import com.cybersoft.capstone.repository.UserRepository;
import com.cybersoft.capstone.service.interfaces.OrderService;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, PaymentMethodRepository paymentMethodRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Transactional
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(orderDTO.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        Users users = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Platform not found"));

        Orders orders = new Orders();
        orders.setPaymentMethod(paymentMethod);
        orders.setOrderStatus(orderDTO.getOrderStatus());
        orders.setPaymentStatus(orderDTO.getPaymentStatus());
        orders.setOriginalAmount(orderDTO.getOriginalAmount());
        orders.setDiscountedAmount(orderDTO.getDiscountedAmount());
        orders.setTotalAmount(orderDTO.getTotalAmount());
        orders.setUsers(users);

        Orders savedOrder = orderRepository.save(orders);

        orderDTO.setId(savedOrder.getId());
        return orderDTO;
    }

    @Transactional
    @Override
    public void softDeleteOrderById(int id) {
        orderRepository.findById(id)
                .ifPresentOrElse(order -> {
                    order.setDeletedOn(Timestamp.valueOf(LocalDateTime.now()));
                }, () -> {throw new RuntimeException("Game not found");});

    }
}
