package com.cybersoft.capstone.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cybersoft.capstone.entity.CartItem;
import com.cybersoft.capstone.entity.Carts;
import com.cybersoft.capstone.entity.Games;

import lombok.Data;

@Data
public class CartDetailDTO {
    private List<GameItemDTO> cartDetail;

    public CartDetailDTO(Carts cart) {
        cartDetail = new ArrayList<GameItemDTO>();
        List<CartItem> cartItems = cart.getCartItems();

        for (int i = 0; i < cartItems.size(); i++) {
            Games currentGame = cartItems.get(i).getGames();
            if (cartDetail.size() == 0) {
                addGameToGameItems(currentGame);
            } else if (checkIfGameAlreadyAdded(currentGame)) {
                increaseGameQuantity(currentGame);
            } else {
                addGameToGameItems(currentGame);
            }
        }
    }

    private void increaseGameQuantity(Games currentGame) {
        for (int i = 0; i < cartDetail.size(); i++) {
            GameItemDTO currentGameItem = cartDetail.get(i);
            if (currentGameItem.getGameId() == currentGame.getId()); {
                currentGameItem.setQuantity(currentGameItem.getQuantity() + 1);
                break;
            }
        }
        
    }

    private void addGameToGameItems(Games currentGame) {
        GameItemDTO newGameItem = new GameItemDTO();
        System.out.println(currentGame.getId());
        newGameItem.setGameId(currentGame.getId());
        newGameItem.setImg(currentGame.getMedias().get(0).getUrl()); //TODO: get(0)
        newGameItem.setPrice(currentGame.getPrice());
        newGameItem.setTitle(currentGame.getTitle());
        SaleDTO sale = new SaleDTO();
        sale.setId(currentGame.getSale().getId());
        sale.setAmount(currentGame.getSale().getAmount());
        sale.setStartDate(currentGame.getSale().getStartDate());
        sale.setEndDate(currentGame.getSale().getEndDate());
        newGameItem.setSale(sale);
        newGameItem.setQuantity(1);
        cartDetail.add(newGameItem);
    }

    private Boolean checkIfGameAlreadyAdded(Games currentGame) {
        Boolean isAdded = false;
        Iterator<GameItemDTO> iterator = cartDetail.iterator();

        while (iterator.hasNext()) {
            GameItemDTO gameItem = iterator.next();
            if (gameItem.getGameId() == currentGame.getId()) {
                isAdded = true;
                break;
            }
        }

        return isAdded;
    }
}

