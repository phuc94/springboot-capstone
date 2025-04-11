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

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        Optional<Admins> adminsOptional = adminRepository.findByEmail(email);

        if (adminsOptional.isPresent()) {
            Admins admin = adminsOptional.get();

            // Verify old password
            if (passwordEncoder.matches(oldPassword, admin.getPassword())) {
                // Encode and set new password
                admin.setPassword(passwordEncoder.encode(newPassword));
                adminRepository.save(admin);
                return true;
            }
        }
        return false;
    }
}
