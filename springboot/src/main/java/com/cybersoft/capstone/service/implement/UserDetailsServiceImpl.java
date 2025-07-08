package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) {
        Users user = userService.getUserByEmail(email);
        return new CustomUserDetails(user);
    }
}
