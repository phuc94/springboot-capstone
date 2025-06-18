package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.CartDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.mapper.CartMapper;
import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.request.UpdateCartRequest;
import com.cybersoft.capstone.repository.CartRepository;
import com.cybersoft.capstone.service.interfaces.CartItemService;
import com.cybersoft.capstone.service.interfaces.CartService;
import com.cybersoft.capstone.service.interfaces.ClientGameService;
import com.cybersoft.capstone.service.interfaces.GameKeyService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final ClientGameService clientGameService;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final GameKeyService gameKeyService;
    private final CartItemService cartItemService;

    public CartServiceImpl(
        CartRepository cartRepository,
        CartMapper cartMapper,
        GameKeyService gameKeyService,
        CartItemService cartItemService,
        ClientGameService clientGameService
        ) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.gameKeyService = gameKeyService;
        this.cartItemService = cartItemService;
        this.clientGameService = clientGameService;
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll()
                .stream().map(cartMapper::toCartDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartDetailDTO getCartDetailByCartId(int cartId, CustomUserDetails user) {
        if (cartItemService.existsByCartId(cartId)) {
            return new CartDetailDTO(cartItemService.findByCartsId(cartId));
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
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

    // TODO: check quantity with stock
    @Override
    @Transactional
    public CartDetailDTO addItemToCart(int gameId, CustomUserDetails user) {
        Games clientGame = clientGameService.getGameById(gameId);
        int stock = clientGame.getStock();
        if (stock == 0) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
        }

        Boolean isCartUpdated = false;
        Carts cart = cartRepository.findById(user.getCart().getId())
            .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
        List<CartItem> cartItems = cart.getCartItems();

        // Check is Game already added to Cart
        for (int i = 0; i < cartItems.size(); i ++) {
            CartItem currentCartItem = cartItems.get(i);
            if (currentCartItem.getGames().getId() == gameId) {
                currentCartItem.setQuantity(currentCartItem.getQuantity() + 1);
                cartItems.set(i, cartItemService.save(currentCartItem));
                isCartUpdated = true;
                break;
            }
        }
        if (isCartUpdated) {
            return this.getCartDetailByCartId(cart.getId(), user);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCarts(cart);
        cartItem.setQuantity(1);
        Games gameRef = new Games();
        gameRef.setId(clientGame.getId());
        gameRef.setPrice(clientGame.getPrice());
        gameRef.setMedias(clientGame.getMedias());
        gameRef.setSale(clientGame.getSale());
        cartItem.setGames(gameRef);
        cartItemService.save(cartItem);

        return this.getCartDetailByCartId(cart.getId(), user);
    }

    @Override
    public void updateCartItem(UpdateCartRequest updateCartRequest, int cartId) {
        if (cartItemService.existsByGameIdAndCartId(updateCartRequest.getGameId(), cartId)) {
            CartItem cartItem = cartItemService.findByGameIdAndCartId(updateCartRequest.getGameId(), cartId);
            cartItem.setQuantity(updateCartRequest.getQuantity());
            cartItemService.save(cartItem);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
