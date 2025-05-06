package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.ReviewDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> getAllReviews();
    public ReviewDTO getReviewById(int id);
    public ReviewDTO createReview(@Valid ReviewDTO reviewDTO);
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO);
    public void deleteReviewById(int id);
}
