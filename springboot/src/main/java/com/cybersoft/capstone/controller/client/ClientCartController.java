package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.CartService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class ClientCartController {

    private final CartService cartService;

    public ClientCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public BaseResponse<CartDetailDTO> getCartByUserId(@AuthenticationPrincipal CustomUserDetails user) {
        int id = user.getId();
        return new OkResponse<CartDetailDTO>(cartService.getCartById(id));
    }

    @PostMapping("/{gameId}")
    public BaseResponse<CartDetailDTO> addItemToCart(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int gameId) {
        return new OkResponse<CartDetailDTO>(cartService.addItemToCart(gameId, user));
    }

    @DeleteMapping("/{gameId}")
    public BaseResponse<Void> deleteItemFromCart(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int gameId) {
        try {
            cartService.removeItemFromCart(gameId, user);
        } catch (Error e) {
            throw new NotFoundException("Game not found");
        }
        return new AcceptedResponse<>();
    }

}
