package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CartItemRepository;
import com.cybersoft.capstone.service.interfaces.CartItemService;

import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Boolean existsByGameIdAndCartId(int gameId, int cartId) {
        try {
            cartItemRepository.existsByGamesIdAndCartsId(gameId, cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
        return true;
    }

    @Override
    public void deleteByGameIdAndCartId(int gameId, int cartId) {
        try {
          cartItemRepository.deleteByGamesIdAndCartsId(gameId, cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }


}

