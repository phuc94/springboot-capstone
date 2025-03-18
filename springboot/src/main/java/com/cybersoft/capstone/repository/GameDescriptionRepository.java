package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.GameDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDescriptionRepository extends JpaRepository<GameDescription, Integer> {
}
