package com.cybersoft.capstone.repository;

import java.util.Optional;

import com.cybersoft.capstone.entity.Admins;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Integer> {
    Optional<Admins> findByEmailAndPassword(String email, String password);
    Optional<Admins> findByEmail(String email);
    boolean existsByEmail(String email);
}
