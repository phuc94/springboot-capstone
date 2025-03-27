package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.payload.response.BaseResponse;

import java.util.List;

public interface RoleService {
    public BaseResponse<List<Roles>> getAllRoles();
    public BaseResponse<Roles> getRoleById(int id);
    public BaseResponse<Roles> createRole(Roles role);
    public BaseResponse<Roles> updateRole(int id, Roles role);
    public BaseResponse<Void> deleteRoleById(int id);
}
