package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Reviews, Integer> {
    Boolean existsByUserIdAndGameId(int userId, int gameId);
}
