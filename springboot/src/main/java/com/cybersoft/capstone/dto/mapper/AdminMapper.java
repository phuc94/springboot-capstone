package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminDTO;
import com.cybersoft.capstone.entity.Admins;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admins toAdmins(AdminDTO adminDTO);
    AdminDTO toAdminDTO(Admins admin);
}
