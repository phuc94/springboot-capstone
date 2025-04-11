package com.cybersoft.capstone.service.interfaces;

public interface AuthenticationService {
    public String authenticate(String email, String password);
    public boolean changePassword(String email, String oldPassword, String newPassword);
}
