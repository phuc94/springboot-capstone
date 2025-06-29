package com.cybersoft.capstone.dto;

import java.util.ArrayList;
import java.util.List;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Games;

import lombok.Data;

@Data
public class CartDetailDTO {
    private List<GameItemDTO> items;
    private int originalPrice;
    private int saleAmount;
    private int discountAmount;
    private int subTotalAmount;
    private int totalAmount;

    public CartDetailDTO(List<CartItem> cartItems) {
        items = new ArrayList<GameItemDTO>();

        for (int i = 0; i < cartItems.size(); i++) {
            CartItem currentCartItem = cartItems.get(i);
            Games currentGame = currentCartItem.getGames();
            GameItemDTO newGameItem = new GameItemDTO();
            newGameItem.setGameId(currentGame.getId());
            newGameItem.setTitle(currentGame.getTitle());
            newGameItem.setPrice(currentGame.getPrice());
            newGameItem.setQuantity(currentCartItem.getQuantity());
            newGameItem.setImg(currentGame.getMedias().get(0).getUrl());
            SaleDTO sale = new SaleDTO();
            sale.setId(currentGame.getSale().getId());
            sale.setAmount(currentGame.getSale().getAmount());
            sale.setStartDate(currentGame.getSale().getStartDate());
            sale.setEndDate(currentGame.getSale().getEndDate());
            newGameItem.setSale(sale);

            originalPrice = originalPrice + (currentGame.getPrice() * currentCartItem.getQuantity());
            saleAmount = saleAmount + (
                currentGame.getPrice() * currentGame.getSale().getAmount() * currentCartItem.getQuantity() / 100
                );

            items.add(newGameItem);
        }

        subTotalAmount = originalPrice - saleAmount;
        totalAmount = subTotalAmount;
    }

}

