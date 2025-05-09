package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.entity.GameDescription;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Platforms;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(source = "gameDTO.id", target = "id")
    @Mapping(source = "gameDTO.title", target = "title")
    Games toGames(AdminGameDTO gameDTO, Platforms platform, GameDescription gameDescription);

    @Mapping(source = "platform.id", target = "platformId")
    @Mapping(source = "gameDescription.id", target = "descriptionId")
    AdminGameDTO toAdminGameDTO(Games games);
}
