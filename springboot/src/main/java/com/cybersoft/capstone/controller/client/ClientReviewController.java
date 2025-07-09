package com.cybersoft.capstone.controller.client;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.payload.request.CreateReviewRequest;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.payload.response.OkResponse;
import com.cybersoft.capstone.service.interfaces.ReviewService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/review")
public class ClientReviewController {
    private ReviewService reviewService;

    public ClientReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
  
    @PostMapping
    public BaseResponse<ReviewDTO> addReviewToGame(
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid @RequestBody CreateReviewRequest reqBody
    ) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setUser(user.getSimpleUserDTO());
        reviewDTO.setComment(reqBody.getComment());
        reviewDTO.setGameId(reqBody.getGameId());
        reviewDTO.setRating(reqBody.getRating());
        return new OkResponse<ReviewDTO>(reviewService.createClientReview(reviewDTO, reqBody.getOrderId()));
    }

}

