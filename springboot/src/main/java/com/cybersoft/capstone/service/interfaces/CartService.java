package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.payload.request.UpdateCartRequest;

public interface CartService {
    public List<CartDTO> getAllCarts();
    public CartDetailDTO getCartDetailByCartId(int cartId, CustomUserDetails user);
    public Carts createCart(Carts cart);
    public CartDTO updateCart(int id, CartDTO cartDTO);
    public Boolean removeItemFromCart(int gameId, CustomUserDetails user);
    public CartDetailDTO addItemToCart(int itemId, CustomUserDetails user);
    public void updateCartItem(UpdateCartRequest updateCartRequest, int cartId);
}
