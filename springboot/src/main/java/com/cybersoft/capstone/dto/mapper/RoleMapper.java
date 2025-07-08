package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.RoleDTO;
import com.cybersoft.capstone.entity.Roles;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Roles toRole(RoleDTO roleDTO);
    RoleDTO toRoleDTO(Roles role);
}
