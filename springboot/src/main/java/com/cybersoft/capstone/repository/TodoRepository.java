package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {}
