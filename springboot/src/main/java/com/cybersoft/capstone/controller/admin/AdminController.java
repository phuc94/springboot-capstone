package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.AdminDTO;
import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.AdminService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResponse<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        return new OkResponse<AdminDTO>(adminService.createAdmin(adminDTO));
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
