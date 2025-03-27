package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.entity.Roles;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.payload.response.AcceptedResponse;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.repository.RoleRepository;
import com.cybersoft.capstone.service.interfaces.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public BaseResponse<List<Roles>> getAllRoles() {
        return new OkResponse<>(roleRepository.findAll());
    }

    @Override
    public BaseResponse<Roles> getRoleById(int id) {
        return roleRepository.findById(id)
                .map(OkResponse::new)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Roles> createRole(Roles role) {
        return new OkResponse<>(roleRepository.save(role));
    }

    @Override
    public BaseResponse<Roles> updateRole(int id, Roles role) {
        return roleRepository.findById(id)
                .map(foundRole -> {
                    foundRole.setName(role.getName());
                    foundRole.setDescription(role.getDescription());
                    return new OkResponse<>(roleRepository.save(foundRole));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public BaseResponse<Void> deleteRoleById(int id) {
        if(roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return new AcceptedResponse<>();
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

}
