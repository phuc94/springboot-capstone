package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genres, Integer> {
}
