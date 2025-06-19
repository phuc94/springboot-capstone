package com.cybersoft.capstone.service.implement;

import java.util.List;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CartItemRepository;
import com.cybersoft.capstone.service.interfaces.CartItemService;

import org.springframework.http.HttpStatus;
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
            return cartItemRepository.existsByGamesIdAndCartsId(gameId, cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }  

    @Override
    public Boolean existsByCartId(int cartId) {
        try {
            return cartItemRepository.existsByCartsId(cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }

    @Override
    public void deleteByGameIdAndCartId(int gameId, int cartId) {
        try {
          cartItemRepository.deleteByGamesIdAndCartsId(gameId, cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }

    @Override
    public CartItem findById(int id) {
        return cartItemRepository.findById(id)
              .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public CartItem findByGameIdAndCartId(int gameId, int cartId) {
        return cartItemRepository.findByGamesIdAndCartsId(gameId, cartId)
              .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public List<CartItem> findByCartsId(int cartId) {
        return cartItemRepository.findByCartsId(cartId);
    }

    @Override
    public void deleteAll(List<CartItem> cartItems) {
        cartItemRepository.deleteAll(cartItems);
    }

}

