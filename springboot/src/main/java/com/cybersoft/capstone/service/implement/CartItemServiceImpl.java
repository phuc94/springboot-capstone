package com.cybersoft.capstone.service.implement;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        cartItem.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Boolean existsByGameIdAndCartId(int gameId, int cartId) {
        try {
            return cartItemRepository.existsByGamesIdAndCartsIdAndDeletedAtIsNull(gameId, cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }  

    @Override
    public Boolean existsByCartId(int cartId) {
        try {
            return cartItemRepository.existsByCartsIdAndDeletedAtIsNull(cartId);
        } catch (Error e) {
            throw new NotFoundException("Not found!");
        }
    }

    @Override
    public void deleteByGameIdAndCartId(int gameId, int cartId) {
        try {
          cartItemRepository.deleteByGamesIdAndCartsIdAndDeletedAtIsNull(gameId, cartId);
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
        return cartItemRepository.findByGamesIdAndCartsIdAndDeletedAtIsNull(gameId, cartId)
              .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public List<CartItem> findByCartsId(int cartId) {
        return cartItemRepository.findByCartsIdAndDeletedAtIsNullOrderByCreatedAtDesc(cartId);
    }

    @Override
    public void deleteAll(List<CartItem> cartItems) {
        List<CartItem> mappedCartItems = cartItems.stream().map(cartItem -> {
            cartItem.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
            return cartItem;
        }).collect(Collectors.toList());
        cartItemRepository.saveAll(mappedCartItems);
    }

}

