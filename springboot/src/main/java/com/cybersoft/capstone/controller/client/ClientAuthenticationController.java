package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.request.SignInRequest;
import com.cybersoft.capstone.payload.request.SignUpRequest;
import com.cybersoft.capstone.payload.response.AuthResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.RoleService;
import com.cybersoft.capstone.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class ClientAuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public BaseResponse<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        Users user = new Users();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        Roles role = roleService.getRoleByTitle("ROLE_USER");
        user.setRole(role);

        return new OkResponse<>(userService.signUp(user));
    }

    @PostMapping("/login")
    public BaseResponse<AuthResponse> login(@RequestBody SignInRequest signInRequest) {
        Users user = new Users();
        user.setPassword(signInRequest.getPassword());
        user.setEmail(signInRequest.getEmail());
        return new OkResponse<>(userService.signIn(user));
    }

}
