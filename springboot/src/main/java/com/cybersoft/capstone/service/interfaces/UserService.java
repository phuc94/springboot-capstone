package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface UserService {
    BaseResponse<Users> updateUser(int id, Users user);
    BaseResponse<Users> getUserById(int id);
    BaseResponse<Users> signUp(Users user);
    BaseResponse<String> signIn(String email, String password);
} 