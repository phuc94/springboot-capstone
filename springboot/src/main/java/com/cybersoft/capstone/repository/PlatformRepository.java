package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlatformRepository extends JpaRepository<Platforms, Integer>, JpaSpecificationExecutor<Platforms> {
}
