package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.AdminRepository;
import com.cybersoft.capstone.service.interfaces.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public BaseResponse<List<Admins>> getAllAdmins() {
        return new OkResponse<>(adminRepository.findAll());
    }

    @Override
    public BaseResponse<Admins> getAdminById(int id) {
        return adminRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Admins> createAdmin(Admins admin) {
        return new OkResponse<>(adminRepository.save(admin));
    }

    @Override
    public BaseResponse<Admins> updateAdmin(int id, Admins admin) {
        return adminRepository.findById(id)
                .map(foundAdmin -> {
                    foundAdmin.setFullname(admin.getFullname());
                    foundAdmin.setEmail(admin.getEmail());
                    foundAdmin.setPassword(admin.getPassword());
                    foundAdmin.setRole(admin.getRole());
                    return new OkResponse<>(adminRepository.save(foundAdmin));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteAdminById(int id) {
        if(adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
