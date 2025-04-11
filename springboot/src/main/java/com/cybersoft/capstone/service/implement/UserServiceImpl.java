package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.exception.BadRequestException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.UserRepository;
import com.cybersoft.capstone.service.interfaces.UserService;
import com.cybersoft.capstone.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public BaseResponse<Users> signUp(Users user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        Users savedUser = userRepository.save(user);
        return new OkResponse<>(savedUser);
    }

    @Override
    public BaseResponse<String> signIn(String email, String password) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new BadRequestException("Invalid email or password");
        }

        Users user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtHelper.generateToken(user.getEmail());
        return new OkResponse<>(token);
    }
} 