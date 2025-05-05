package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.request.SignInRequest;
import com.cybersoft.capstone.payload.request.SignUpRequest;
import com.cybersoft.capstone.payload.response.AuthenticationResponse;
import com.cybersoft.capstone.service.interfaces.ClientAuthenticationService;
import com.cybersoft.capstone.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class ClientAuthenticationController {

    @Autowired
    private ClientAuthenticationService authenticationService;

    @Autowired
    private UserService userService;

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
