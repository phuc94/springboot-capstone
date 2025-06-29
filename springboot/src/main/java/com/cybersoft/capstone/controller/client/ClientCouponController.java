package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.dto.CartDetailCouponDTO;
import com.cybersoft.capstone.dto.CartDetailDTO;
import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.CartService;
import com.cybersoft.capstone.service.interfaces.CouponService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class ClientCouponController {

    private CouponService couponService;
    private CartService cartService;

    public ClientCouponController(CouponService couponService, CartService cartService) {
        this.couponService = couponService;
        this.cartService = cartService;
    }

    @PostMapping("/{code}")
    public BaseResponse<CartDetailCouponDTO> applyCoupon(@AuthenticationPrincipal CustomUserDetails user, @PathVariable String code) {
        CartDetailDTO cartDetailDTO = cartService.getCartDetailByCartId(user.getCart().getId());
        return new OkResponse<CartDetailCouponDTO>(couponService.applyCoupon(code, cartDetailDTO));
    }

}
