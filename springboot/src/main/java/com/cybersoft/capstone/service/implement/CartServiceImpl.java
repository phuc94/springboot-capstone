package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.mapper.CartMapper;
import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.entity.GameKey;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.CartRepository;
import com.cybersoft.capstone.service.interfaces.CartItemService;
import com.cybersoft.capstone.service.interfaces.CartService;
import com.cybersoft.capstone.service.interfaces.GameKeyService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final GameKeyService gameKeyService;
    private final CartItemService cartItemService;

    public CartServiceImpl(
        CartRepository cartRepository,
        CartMapper cartMapper,
        GameKeyService gameKeyService,
        CartItemService cartItemService
        ) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.gameKeyService = gameKeyService;
        this.cartItemService = cartItemService;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll()
                .stream().map(cartMapper::toCartDTO).collect(Collectors.toList());
    }

    @Override
    public CartDetailDTO getCartById(int id) {
        return cartRepository.findById(id)
                .map(cart -> new CartDetailDTO(cart))
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public Carts createCart(Carts cart) {
        return cartRepository.save(cart);
    }

    @Override
    public CartDTO updateCart(int id, CartDTO cartDTO) {
        return cartRepository.findById(id)
                .map(foundCart -> {
                    foundCart.setStatus(cartDTO.getStatus());
                    return cartMapper.toCartDTO(cartRepository.save(foundCart));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    @Transactional
    public Boolean removeItemFromCart(int gameId, CustomUserDetails user) {
        Carts cart = user.getCart();
        if (cartItemService.existsByGameIdAndCartId(gameId, cart.getId())) {
            cartItemService.deleteByGameIdAndCartId(gameId, cart.getId());
            return true;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    @Override
    @Transactional
    public CartDetailDTO addItemToCart(int gameId, CustomUserDetails user) {
        Carts cart = user.getCart();

        GameKey gameKey = gameKeyService.getAvailableGameKey(gameId);
        gameKey.setActivated(true);
        gameKeyService.updateGameKey(gameKey.getId(), gameKey);

        CartItem cartItem = new CartItem();
        cartItem.setCarts(cart);
        cartItem.setGames(gameKey.getGame());
        CartItem savedCartItem = cartItemService.save(cartItem);

        cart.addItemToCart(savedCartItem);
        Carts cartUpdated = cartRepository.save(cart);
        return new CartDetailDTO(cartUpdated);
    }
}
