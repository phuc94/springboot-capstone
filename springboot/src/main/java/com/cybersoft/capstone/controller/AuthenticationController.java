package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.payload.response.AuthenticationResponse;
import com.cybersoft.capstone.service.interfaces.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        String token = authenticationService.authenticate(email, password);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setData(token);

        return ResponseEntity.ok(authenticationResponse);
    }

}
