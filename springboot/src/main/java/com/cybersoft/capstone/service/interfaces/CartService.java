package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.CartDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CartService {
    public List<CartDTO> getAllCarts();
    public CartDTO getCartById(int id);
    public CartDTO createCart(@Valid CartDTO cartDTO);
    public CartDTO updateCart(int id, CartDTO cartDTO);
    public void deleteCartById(int id);
}
