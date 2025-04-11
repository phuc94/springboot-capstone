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

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestParam String email,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        boolean success = authenticationService.changePassword(email, oldPassword, newPassword);

        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to change password. Please check your credentials.");
        }
    }
}
