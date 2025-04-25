package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.enums.CartStatus;
import lombok.Data;

@Data
public class CartDTO {
    private CartStatus status;
}
