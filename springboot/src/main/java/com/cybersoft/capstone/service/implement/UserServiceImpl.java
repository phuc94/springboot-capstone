package com.cybersoft.capstone.service.implement;

import java.util.Optional;

import com.cybersoft.capstone.dto.UserDTO;
import com.cybersoft.capstone.dto.mapper.UserMapper;
import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.exception.BadRequestException;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.UserRepository;
import com.cybersoft.capstone.service.interfaces.UserService;
import com.cybersoft.capstone.utils.JwtHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;
  
    @Override
    public UserDTO updateUser(int id, UserDTO user) {
        return userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setName(user.getName());
                    foundUser.setEmail(user.getEmail());
                    return userMapper.toUserDTO(userRepository.save(foundUser));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public UserDTO getUserById(int id) {
        return userRepository.findById(id)
                .map(user -> {
                    return userMapper.toUserDTO(user);
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public UserDTO signUp(Users user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        Users savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

    @Override
    public String signIn(Users user) {
        Optional<Users> userOptional = userRepository.findByEmail(user.getEmail());
        
        if (userOptional.isEmpty()) {
            throw new BadRequestException("Invalid email or password");
        }

        Users userDB = userOptional.get();
        if (!passwordEncoder.matches(user.getPassword(), userDB.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtHelper.generateToken(user.getEmail());
        return token;
    }
} 
