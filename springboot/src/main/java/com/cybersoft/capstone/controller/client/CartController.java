package com.cybersoft.capstone.controller.client;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.CartService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public BaseResponse<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        BaseResponse<List<CartDTO>> response = new BaseResponse<>(200, "Lấy danh sách giỏ hàng thành công");
        response.setData(carts);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<CartDTO> getCartById(@Valid @PathVariable int id) {
        CartDTO cart = cartService.getCartById(id);
        BaseResponse<CartDTO> response = new BaseResponse<>(200, "Lấy giỏ hàng theo ID thành công");
        response.setData(cart);
        return response;
    }

    @PostMapping
    public BaseResponse<CartDTO> createCart(@Valid @RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.createCart(cartDTO);
        BaseResponse<CartDTO> response = new BaseResponse<>(201, "Tạo giỏ hàng mới thành công");
        response.setData(createdCart);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<CartDTO> updateCart(@Valid @PathVariable int id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, cartDTO);
        BaseResponse<CartDTO> response = new BaseResponse<>(200, "Cập nhật giỏ hàng thành công");
        response.setData(updatedCart);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteCartById(@Valid @PathVariable int id) {
        cartService.deleteCartById(id);
        return new BaseResponse<>(200, "Xóa giỏ hàng thành công");
    }

}
