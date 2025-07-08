package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.Optional;

import com.cybersoft.capstone.dto.AdminDTO;
import com.cybersoft.capstone.dto.mapper.AdminMapper;
import com.cybersoft.capstone.entity.Admins;
import com.cybersoft.capstone.exception.BadRequestException;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.AdminAuthResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.AdminRepository;
import com.cybersoft.capstone.service.interfaces.AdminService;
import com.cybersoft.capstone.utils.JwtHelper;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;
    private JwtHelper jwtHelper;
    private AdminMapper adminMapper;

    public AdminServiceImpl(
        AdminRepository adminRepository,
        PasswordEncoder passwordEncoder,
        JwtHelper jwtHelper,
        AdminMapper adminMapper
    ) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminMapper = adminMapper;
        this.jwtHelper = jwtHelper;
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
    public AdminDTO createAdmin(AdminDTO adminDTO) {
        if (adminRepository.existsByEmail(adminDTO.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        Admins admin = adminMapper.toAdmins(adminDTO);
        return adminMapper.toAdminDTO(adminRepository.save(admin));
    }

    @Override
    public BaseResponse<Admins> updateAdmin(int id, Admins admin) {
        return adminRepository.findById(id)
                .map(foundAdmin -> {
                    foundAdmin.setName(admin.getName());
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

    @Override
    public Admins getAdminByEmail(String email) {
        return adminRepository.findByEmail(email)
              .orElseThrow(() -> new NotFoundException("No admin found with that email."));
    }

    @Override
    public AdminAuthResponse logIn(AdminDTO admin) {
        Optional<Admins> adminOptional = adminRepository.findByEmail(admin.getEmail());
        
        if (adminOptional.isEmpty()) {
            throw new BadRequestException("Invalid Email");
        }

        Admins adminDb = adminOptional.get();
        if (!passwordEncoder.matches(admin.getPassword(), adminDb.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        // Generate JWT token
        String token = jwtHelper.generateToken(admin.getEmail(), "ROLE_ADMIN");
        AdminAuthResponse authRes = new AdminAuthResponse();
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(adminDb.getId());
        adminDTO.setEmail(adminDb.getEmail());
        adminDTO.setName(adminDb.getName());
        authRes.setToken(token);
        authRes.setAdmin(adminDTO);
        return authRes;
    }
}
