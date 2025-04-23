package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
