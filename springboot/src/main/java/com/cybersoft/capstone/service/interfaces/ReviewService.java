package com.cybersoft.capstone.service.interfaces;

import java.util.List;

import jakarta.validation.Valid;

import com.cybersoft.capstone.dto.ReviewDTO;

public interface ReviewService {
    public List<ReviewDTO> getAllReviews();
    public ReviewDTO getReviewById(int id);
    public ReviewDTO createReview(@Valid ReviewDTO reviewDTO);
    public ReviewDTO createClientReview(@Valid ReviewDTO reviewDTO, int orderId);
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO);
    public void deleteReviewById(int id);
}
