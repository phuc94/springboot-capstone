package com.cybersoft.capstone.controller.admin;

import com.cybersoft.capstone.dto.OrderDTO;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public BaseResponse<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTO = orderService.getAllOrders();
        BaseResponse<List<OrderDTO>> response = new BaseResponse<>(200, "Lấy danh sách order thành công!");
        response.setData(orderDTO);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<OrderDTO> getOrderById(@PathVariable int id) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(id);
            BaseResponse<OrderDTO> response = new BaseResponse<>(200, "Lấy order theo id thành công!");
            response.setData(orderDTO);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<OrderDTO> response = new BaseResponse<>(404, "Order không tồn tại!");
            response.setData(null);
            return response;
        }
    }

    @PostMapping
    public BaseResponse<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO order = orderService.createOrder(orderDTO);
            BaseResponse<OrderDTO> response = new BaseResponse<>(201, "Tạo order thành công!");
            response.setData(order);
            return response;
        } catch (NotFoundException ex) {
            BaseResponse<OrderDTO> response = new BaseResponse<>(500, "Tạo order không thành công!");
            response.setData(null);
            return response;
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteOrderById(@PathVariable int id) {
        try {
            orderService.softDeleteOrderById(id);
            BaseResponse<Void> response = new BaseResponse<>(204, "Hủy order thành công");
            response.setData(null);
            return response;
        } catch (Exception ex) {
            BaseResponse<Void> response = new BaseResponse<>(500, "Hủy order không thành công");
            response.setData(null);
            return response;
        }
    }
}
