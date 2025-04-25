package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Games, Integer> {
}
