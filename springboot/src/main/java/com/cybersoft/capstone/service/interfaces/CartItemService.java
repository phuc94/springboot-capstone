package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.CartItem;

public interface CartItemService {
    public CartItem save(CartItem cartItem);
    public CartItem findById(int id);
    public CartItem findByGameIdAndCartId(int gameId, int cartId);
    public List<CartItem> findByCartsId(int cartId);
    public Boolean existsByGameIdAndCartId(int gameId, int cartId);
    public Boolean existsByCartId(int cartId);
    public void deleteByGameIdAndCartId(int gameId, int cartId);
    public void deleteAll(List<CartItem> cartItems);
}
