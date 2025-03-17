package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.NoPlayers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoPlayerRepository extends JpaRepository<NoPlayers, Integer> {
}
