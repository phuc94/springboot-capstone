package com.cybersoft.capstone.controller.client;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public BaseResponse<Users> getUser(@Valid @PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/{id}")
    public BaseResponse<Users> updateUser(@Valid @PathVariable int id, @Valid @RequestBody Users user) {
        return userService.updateUser(id, user);
    }
} 
