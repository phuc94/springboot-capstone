package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.entity.Reviews;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source="gameId", target = "game.id")
    Reviews toReviews(ReviewDTO reviewDTO);

    @Mapping(source="game.id", target = "gameId")
    ReviewDTO toReviewDTO(Reviews reviews);
}
