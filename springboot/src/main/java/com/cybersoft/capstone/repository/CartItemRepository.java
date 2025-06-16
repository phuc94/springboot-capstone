package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    void existsByGamesIdAndCartsId(int gameId, int cart);
    void deleteByGamesIdAndCartsId(int gameId, int cart);
}

