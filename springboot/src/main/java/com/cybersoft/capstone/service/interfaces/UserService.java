package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface UserService {
    BaseResponse<Users> signUp(Users user);
    BaseResponse<String> signIn(String email, String password);
} 