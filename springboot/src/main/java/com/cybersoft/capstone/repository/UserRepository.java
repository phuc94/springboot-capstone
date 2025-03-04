package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Integer> {}
