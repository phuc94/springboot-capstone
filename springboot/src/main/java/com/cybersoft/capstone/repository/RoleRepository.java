package com.cybersoft.capstone.repository;

import java.util.Optional;

import com.cybersoft.capstone.entity.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByTitle(String title);
}
