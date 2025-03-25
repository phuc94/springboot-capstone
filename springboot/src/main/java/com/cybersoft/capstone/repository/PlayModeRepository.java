package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.PlayModes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayModeRepository extends JpaRepository<PlayModes, Integer> {
}
