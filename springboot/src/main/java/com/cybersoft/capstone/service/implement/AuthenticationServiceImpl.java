package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.repository.AdminRepository;
import com.cybersoft.capstone.service.interfaces.AuthenticationService;
import com.cybersoft.capstone.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String authenticate(String email, String password) {
        String token = "";
        Optional<Admins> adminsOptional = adminRepository.findByEmail(email);

        if(adminsOptional.isPresent()) {
            Admins admins = adminsOptional.get();
            if(passwordEncoder.matches(password, admins.getPassword())) {
                token = jwtHelper.generateToken("Hello");
            }
        }
        return token;
    }

}
