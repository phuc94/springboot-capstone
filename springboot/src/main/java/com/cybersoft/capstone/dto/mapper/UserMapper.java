package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.UserCreateDTO;
import com.cybersoft.capstone.dto.UserDTO;
import com.cybersoft.capstone.entity.Users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore= true)
    Users toUserFromUserDTO(UserDTO userDTO);

    @Mapping(target = "id", ignore= true)
    Users toUserFromUserCreateDTO(UserCreateDTO userDTO);

    UserDTO toUserDTO(Users user);
}

