package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Developers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developers, Integer> {
}
