package com.cybersoft.capstone.controller.client;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.dto.SimpleUserDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.ClientReviewService;
import com.cybersoft.capstone.service.interfaces.ReviewService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client/review")
public class ClientReviewController {

    private final ReviewService reviewService;
    private final ClientReviewService clientReviewService;

    public ClientReviewController(ReviewService reviewService, ClientReviewService clientReviewService) {
        this.reviewService = reviewService;
        this.clientReviewService = clientReviewService;
    }

    @GetMapping
    public BaseResponse<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = clientReviewService.getAllReviews();
        BaseResponse<List<ReviewDTO>> response = new BaseResponse<>(200, "Success");
        response.setData(reviews);
        return response;
    }

    @PostMapping
    public BaseResponse<ReviewDTO> createReview(@PathVariable int id, @Valid @RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal CustomUserDetails user) {
        ReviewDTO created = clientReviewService.createReview(reviewDTO, id);
        BaseResponse<ReviewDTO> response = new BaseResponse<>(201, "Created");
        response.setData(created);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<ReviewDTO> updateReview(@PathVariable int id, @Valid @RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal CustomUserDetails user) {
        ReviewDTO updated = clientReviewService.updateReview(id, reviewDTO);
        BaseResponse<ReviewDTO> response = new BaseResponse<>(200, "Updated");
        response.setData(updated);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteReview(@PathVariable int id, @AuthenticationPrincipal CustomUserDetails user) {
        clientReviewService.deleteReviewById(id);
        return new BaseResponse<>(200, "Deleted");
    }
}

