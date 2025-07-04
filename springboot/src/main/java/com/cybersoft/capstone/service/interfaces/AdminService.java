package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface AdminService {
    public BaseResponse<List<Admins>> getAllAdmins();
    public BaseResponse<Admins> getAdminById(int id);
    public BaseResponse<Admins> createAdmin(Admins admin);
    public BaseResponse<Admins> updateAdmin(int id, Admins admin);
    public BaseResponse<Void> deleteAdminById(int id);
}
