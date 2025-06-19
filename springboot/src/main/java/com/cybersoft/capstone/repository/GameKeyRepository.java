package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.GameKey;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameKeyRepository extends JpaRepository<GameKey, Integer> {
    Optional<GameKey> findFirst1ByGameIdAndActivatedFalse(int gameId);

    List<GameKey> findByGameIdAndActivatedIsFalse(int gameId, Pageable pageable);

    default List<GameKey> findTopNByGameIdAndActivatedIsFalse(int gameId, int n) {
        return findByGameIdAndActivatedIsFalse(gameId, PageRequest.of(0, n));
    }
}
