package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.RoleService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public BaseResponse<List<Roles>> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public BaseResponse<Roles> getRole(@Valid @PathVariable int id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public BaseResponse<Roles> createRole(@Valid @RequestBody Roles role) {
        return roleService.createRole(role);
    }

    @PostMapping("/{id}")
    public BaseResponse<Roles> updateRole(@Valid @PathVariable int id, @Valid @RequestBody Roles role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteRole(@Valid @PathVariable int id) {
        return roleService.deleteRoleById(id);
    }

}
