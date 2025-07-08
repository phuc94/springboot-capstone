package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.dto.AdminDTO;
import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.payload.response.AdminAuthResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface AdminService {
    public BaseResponse<List<Admins>> getAllAdmins();
    public BaseResponse<Admins> getAdminById(int id);
    public AdminDTO createAdmin(AdminDTO adminDTO);
    public BaseResponse<Admins> updateAdmin(int id, Admins admin);
    public BaseResponse<Void> deleteAdminById(int id);
    public Admins getAdminByEmail(String email);
    public AdminAuthResponse logIn(AdminDTO adminDto);
}
