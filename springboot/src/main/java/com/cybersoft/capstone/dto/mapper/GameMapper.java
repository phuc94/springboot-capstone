package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.entity.Games;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {

    Games toGames(AdminGameDTO gameDTO);

    @Mapping(source = "gameDescription.id", target = "descriptionId")
    @Mapping(source = "sale.id", target = "saleId")
    @Mapping(source = "platform.id", target = "platformId")
    AdminGameDTO toAdminGameDTO(Games games);

    @Mapping(source = "gameDescription.id", target = "descriptionId")
    @Mapping(source = "sale.id", target = "saleId")
    @Mapping(source = "platform.id", target = "platformId")
    ClientGameDTO toClientGameDTO(Games games);
}
