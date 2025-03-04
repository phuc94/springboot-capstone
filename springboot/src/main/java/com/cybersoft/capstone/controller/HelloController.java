package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.User;
import com.cybersoft.capstone.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class HelloController {
    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll();
    }
}
