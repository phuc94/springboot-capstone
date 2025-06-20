package com.cybersoft.capstone.dto.mapper;

import java.util.List;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.ClientGameDetailDTO;
import com.cybersoft.capstone.dto.GameCardDTO;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Medias;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {

    Games toGames(AdminGameDTO gameDTO);

    @Mapping(source = "gameDescription.description", target = "description")
    @Mapping(source = "sale.id", target = "saleId")
    @Mapping(source = "platform.id", target = "platformId")
    @Mapping(expression = "java(getThumbnailImg(games.getMedias()))", target = "media")
    AdminGameDTO toAdminGameDTO(Games games);

    @Mapping(source = "sale.amount", target = "sale")
    @Mapping(source = "platform.id", target = "platformId")
    ClientGameDetailDTO toClientGameDetailDTO(Games games);

    @Mapping(source = "sale.amount", target = "sale")
    ClientGameDTO toClientGameDTO(Games games);

    @Mapping(source = "sale.amount", target = "sale")
    GameCardDTO toGameCardDTO(Games games);

    default String getThumbnailImg(List<Medias> medias) {
        return (medias != null && !medias.isEmpty()) ? medias.get(0).getUrl() : null;
    }

}
