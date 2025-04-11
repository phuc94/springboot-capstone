package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.UserRepository;
import com.cybersoft.capstone.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse<Users> updateUser(int id, Users user) {
        return userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setName(user.getName());
                    foundUser.setEmail(user.getEmail());
                    foundUser.setAddress(user.getAddress());
                    foundUser.setPhone(user.getPhone());
                    return new OkResponse<>(userRepository.save(foundUser));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Users> getUserById(int id) {
        return userRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }
} 