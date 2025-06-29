package com.cybersoft.capstone.service.interfaces;

import com.cybersoft.capstone.dto.ReviewDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ClientReviewService {
    public List<ReviewDTO> getAllReviews();
    public ReviewDTO getReviewById(int id);
    public ReviewDTO createReview(@Valid ReviewDTO reviewDTO, int gameId);
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO);
    public void deleteReviewById(int id);

}
