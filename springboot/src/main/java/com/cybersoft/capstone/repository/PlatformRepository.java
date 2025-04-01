package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platforms, Integer> {
}
