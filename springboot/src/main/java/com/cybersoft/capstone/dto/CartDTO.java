package com.cybersoft.capstone.dto;

import java.util.List;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.enums.CartStatus;

import lombok.Data;

@Data
public class CartDTO {
    private CartStatus status;
    private List<CartItem> cartItems;
}
