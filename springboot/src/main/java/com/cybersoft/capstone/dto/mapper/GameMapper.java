package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.entity.Games;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    Games toGames(AdminGameDTO gameDTO);
    AdminGameDTO toAdminGameDTO(Games games);
}
