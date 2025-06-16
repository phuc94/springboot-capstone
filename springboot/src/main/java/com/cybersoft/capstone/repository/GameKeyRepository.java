package com.cybersoft.capstone.repository;

import java.util.Optional;

import com.cybersoft.capstone.entity.GameKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameKeyRepository extends JpaRepository<GameKey, Integer> {
    Optional<GameKey> findFirst1ByGameIdAndActivatedFalse(int gameId);
}
