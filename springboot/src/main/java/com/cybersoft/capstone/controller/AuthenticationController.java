package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.request.SignInRequest;
import com.cybersoft.capstone.payload.request.SignUpRequest;
import com.cybersoft.capstone.payload.response.AuthenticationResponse;
import com.cybersoft.capstone.service.interfaces.AuthenticationService;
import com.cybersoft.capstone.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        String token = authenticationService.authenticate(email, password);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setData(token);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        Users user = new Users();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());

        return ResponseEntity.ok(userService.signUp(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(userService.signIn(signInRequest.getEmail(), signInRequest.getPassword()));
    }
}
