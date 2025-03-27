package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admins, Integer> {
}
