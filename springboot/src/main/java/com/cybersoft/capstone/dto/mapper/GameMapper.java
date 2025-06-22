package com.cybersoft.capstone.dto.mapper;

import java.time.LocalDateTime;
import java.util.List;

import com.cybersoft.capstone.dto.AdminGameDTO;
import com.cybersoft.capstone.dto.ClientGameCardDTO;
import com.cybersoft.capstone.dto.ClientGameDTO;
import com.cybersoft.capstone.dto.ClientGameDetailDTO;
import com.cybersoft.capstone.dto.SaleDTO;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.Medias;
import com.cybersoft.capstone.entity.Sales;

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

    @Mapping(source = "platform.id", target = "platformId")
    @Mapping(expression = "java(calcSalePrice(games))", target = "salePrice")
    @Mapping(expression = "java(getSale(games))", target = "sale")
    ClientGameDetailDTO toClientGameDetailDTO(Games games);

    @Mapping(expression = "java(calcSalePrice(games))", target = "salePrice")
    @Mapping(expression = "java(getSale(games))", target = "sale")
    ClientGameDTO toClientGameDTO(Games games);

    @Mapping(expression = "java(getThumbnailImg(games.getMedias()))", target = "img")
    @Mapping(expression = "java(calcSalePrice(games))", target = "salePrice")
    @Mapping(expression = "java(getSale(games))", target = "sale")
    ClientGameCardDTO toClientGameCardDTO(Games games);

    default String getThumbnailImg(List<Medias> medias) {
        return (medias != null && !medias.isEmpty()) ? medias.get(0).getUrl() : null;
    }

    default int calcSalePrice(Games game) {
        Sales sale = game.getSale();
        if (sale.getId() == 1 || !sale.getEndDate().toLocalDateTime().isBefore(LocalDateTime.now())) {
            return game.getPrice();
        }
        return game.getPrice() * (100 - sale.getAmount()) / 100;
    }

    default SaleDTO getSale(Games game) {
        Sales sale = game.getSale();
        if (sale.getId() == 1 || !sale.getEndDate().toLocalDateTime().isBefore(LocalDateTime.now())) {
            return null;
        }
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setAmount(sale.getAmount());
        saleDTO.setEndDate(sale.getEndDate());
        return saleDTO;
    }

}
