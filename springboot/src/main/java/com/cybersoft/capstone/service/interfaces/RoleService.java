package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.payload.response.BaseResponse;

public interface RoleService {
    public BaseResponse<List<Roles>> getAllRoles();
    public BaseResponse<Roles> getRoleById(int id);
    public Roles getRoleByTitle(String title);
    public BaseResponse<Roles> createRole(Roles role);
    public BaseResponse<Roles> updateRole(int id, Roles role);
    public BaseResponse<Void> deleteRoleById(int id);
}
