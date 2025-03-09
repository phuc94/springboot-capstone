package com.cybersoft.capstone.repository;

import com.cybersoft.capstone.entity.Users;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<Users, Integer> {}
