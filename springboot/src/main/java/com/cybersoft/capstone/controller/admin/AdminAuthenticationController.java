package com.cybersoft.capstone.controller.admin;

import com.cybersoft.capstone.dto.AdminDTO;
import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.payload.request.SignInRequest;
import com.cybersoft.capstone.payload.response.AdminAuthResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.AdminService;
import com.cybersoft.capstone.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminAuthenticationController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public BaseResponse<AdminDTO> register(@RequestBody AdminDTO adminRegisterRequest) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setEmail(adminRegisterRequest.getEmail());
        adminDTO.setPassword(adminRegisterRequest.getPassword());
        Roles role = roleService.getRoleByTitle("ROLE_SUPER_ADMIN");
        adminDTO.setRole(role);

        return new OkResponse<AdminDTO>(adminService.createAdmin(adminDTO));
    }

    @PostMapping("/login")
    public BaseResponse<AdminAuthResponse> login(@RequestBody SignInRequest signInRequest) {
        AdminDTO admin = new AdminDTO();
        admin.setPassword(signInRequest.getPassword());
        admin.setEmail(signInRequest.getEmail());
        return new OkResponse<>(adminService.logIn(admin));
    }


}
