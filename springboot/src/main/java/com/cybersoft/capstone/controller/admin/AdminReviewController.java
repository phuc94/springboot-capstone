package com.cybersoft.capstone.controller.admin;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.payload.response.BaseResponse;
import com.cybersoft.capstone.service.interfaces.ReviewService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/review")
public class AdminReviewController {

    private final ReviewService reviewService;

    public AdminReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public BaseResponse<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        BaseResponse<List<ReviewDTO>> response = new BaseResponse<>(200, "Lấy danh sách review thành công");
        response.setData(reviews);
        return response;
    }

    @GetMapping("/{id}")
    public BaseResponse<ReviewDTO> getReviewById(@Valid @PathVariable int id) {
        ReviewDTO review = reviewService.getReviewById(id);
        BaseResponse<ReviewDTO> response = new BaseResponse<>(200, "Lấy review theo id thành công");
        response.setData(review);
        return response;
    }

    @PostMapping
    public BaseResponse<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        BaseResponse<ReviewDTO> response = new BaseResponse<>(201, "Tạo review thành công");
        response.setData(createdReview);
        return response;
    }

    @PostMapping("/{id}")
    public BaseResponse<ReviewDTO> updateReview(@Valid @PathVariable int id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        BaseResponse<ReviewDTO> response = new BaseResponse<>(200, "Cập nhật review thành công");
        response.setData(updatedReview);
        return response;
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteReviewById(@Valid @PathVariable int id) {
        reviewService.deleteReviewById(id);
        return new BaseResponse<>(200, "Xóa review thành công");
    }

}


