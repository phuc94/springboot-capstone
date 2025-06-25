package com.cybersoft.capstone.repository;

import java.util.List;

import com.cybersoft.capstone.entity.Platforms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlatformRepository extends JpaRepository<Platforms, Integer>, JpaSpecificationExecutor<Platforms> {
    Platforms findByName(String name);
    List<Platforms> findByParentId(int parentId);
}
