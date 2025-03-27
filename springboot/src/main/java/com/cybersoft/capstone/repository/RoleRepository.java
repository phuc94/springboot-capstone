package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
}
