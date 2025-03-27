package com.cybersoft.capstone.controller;

import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.AdminService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public BaseResponse<List<Admins>> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public BaseResponse<Admins> getAdmin(@Valid @PathVariable int id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public BaseResponse<Admins> createAdmin(@Valid @RequestBody Admins admins) {
        return adminService.createAdmin(admins);
    }

    @PostMapping("/{id}")
    public BaseResponse<Admins> updateAdmin(@Valid @PathVariable int id, @Valid @RequestBody Admins admins) {
        return adminService.updateAdmin(id, admins);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteAdmin(@Valid @PathVariable int id) {
        return adminService.deleteAdminById(id);
    }

}
