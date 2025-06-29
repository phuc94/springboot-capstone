package com.cybersoft.capstone.dto;

import java.util.ArrayList;
import java.util.List;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Coupons;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CartDetailCouponDTO extends CartDetailDTO {
    private List<GameItemDTO> items;
    private int originalPrice;
    private int saleAmount;
    private int subTotalAmount;
    private int discountAmount;
    private int totalAmount;
    private String code;

    public CartDetailCouponDTO(CartDetailDTO cartDetailDTO, Coupons coupon) {
        super(new ArrayList<CartItem>());
        items = cartDetailDTO.getItems();
        originalPrice = cartDetailDTO.getOriginalPrice();
        saleAmount = cartDetailDTO.getSaleAmount();
        subTotalAmount = cartDetailDTO.getSubTotalAmount();
        code = coupon.getCode();

        switch (coupon.getCouponUnit().toString()) {
          case "PERCENTAGE":
            discountAmount = subTotalAmount * coupon.getDiscountAmount() / 100;
            break;
          case "FIXED":
            discountAmount = coupon.getDiscountAmount();
            break;
          default:
            break;
        }

        totalAmount = subTotalAmount - discountAmount;
    }
}

