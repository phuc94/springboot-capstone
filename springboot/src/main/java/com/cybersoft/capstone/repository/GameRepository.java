package com.cybersoft.capstone.repository;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.entity.Games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameRepository extends JpaRepository<Games, Integer>, JpaSpecificationExecutor<Games> {

    List<Games> findByDeletedOnIsNull();

    Optional<Games> findByIdAndDeletedOnIsNull(Integer id);

}
