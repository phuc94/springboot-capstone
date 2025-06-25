package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Boolean existsByGamesIdAndCartsIdAndDeletedAtIsNull(int gameId, int cart);
    Optional<CartItem> findByGamesIdAndCartsIdAndDeletedAtIsNull(int gameId, int cartId);
    List<CartItem> findByCartsIdAndDeletedAtIsNullOrderByCreatedAtDesc(int cartId);
    Boolean existsByCartsIdAndDeletedAtIsNull(int cartId);
    void deleteByGamesIdAndCartsIdAndDeletedAtIsNull(int gameId, int cart);
}

