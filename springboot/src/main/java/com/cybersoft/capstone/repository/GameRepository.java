package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.Games;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Games, Integer> {

    List<Games> findByDeletedOnIsNull();

    Optional<Games> findByIdAndDeletedOnIsNull(Integer id);

}
