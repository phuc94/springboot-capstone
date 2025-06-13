package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.UserDTO;
import com.cybersoft.capstone.entity.Users;

public interface UserService {
    UserDTO updateUser(int id, UserDTO user);
    UserDTO getUserById(int id);
    UserDTO signUp(Users user);
    String signIn(Users user);
} 
