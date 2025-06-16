package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.CartItem;

public interface CartItemService {
    public CartItem save(CartItem cartItem);
    public Boolean existsByGameIdAndCartId(int gameId, int cartId);
    public void deleteByGameIdAndCartId(int gameId, int cartId);
}
