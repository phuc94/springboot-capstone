package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.CustomAdminDetails;
import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.service.interfaces.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public CustomAdminDetails loadUserByUsername(String email) {
        Admins admin = adminService.getAdminByEmail(email);
        return new CustomAdminDetails(admin);
    }
}
