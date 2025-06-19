package com.cybersoft.capstone.dto;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Games;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameItemDTO {
    private int gameId;
    private String title;
    private int price;
    private int quantity;
    private String img;
    private SaleDTO sale;

    public GameItemDTO(CartItem cartItem) {
        Games game = cartItem.getGames();
        this.gameId = game.getId();
        this.title = game.getTitle();
        this.price = game.getPrice();
        this.quantity = cartItem.getQuantity();
        this.img = game.getMedias().get(0).getUrl();
    }
}

