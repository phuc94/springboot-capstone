package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Boolean existsByGamesIdAndCartsId(int gameId, int cart);
    Optional<CartItem> findByGamesIdAndCartsId(int gameId, int cartId);
    List<CartItem> findByCartsIdOrderByCreatedAtDesc(int cartId);
    Boolean existsByCartsId(int cartId);
    void deleteByGamesIdAndCartsId(int gameId, int cart);
}

