package com.cybersoft.capstone.dto.mapper;

import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.entity.Reviews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Reviews toReviews(ReviewDTO reviewDTO);
    ReviewDTO toReviewDTO(Reviews reviews);
}
