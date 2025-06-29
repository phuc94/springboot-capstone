package com.cybersoft.capstone.service.implement;

import com.cybersoft.capstone.dto.CustomUserDetails;
import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.dto.mapper.ReviewMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.service.interfaces.ClientReviewService;
import com.cybersoft.capstone.entity.Reviews;
import com.cybersoft.capstone.entity.Users;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.repository.ReviewRepository;
import com.cybersoft.capstone.repository.UserRepository;
import com.cybersoft.capstone.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientReviewServiceImpl implements ClientReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final ReviewMapper reviewMapper;

    public ClientReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, GameRepository gameRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream().map(reviewMapper::toReviewDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toReviewDTO)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Transactional
    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO, int gameId, CustomUserDetails user) {
        if (reviewDTO.getUser() == null || reviewDTO.getUser().getId() == null) {
            throw new RuntimeException("User must be logged in to review");
        }
        Optional<Users> userOpt = userRepository.findById(reviewDTO.getUser().getId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Optional<Games> gameOpt = gameRepository.findById(gameId);
        if (gameOpt.isEmpty()) {
            throw new RuntimeException("Game not found");
        }
        Reviews review = new Reviews();
        review.setUser(userOpt.get());
        review.setGame(gameOpt.get());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Reviews saved = reviewRepository.save(review);
        // Convert back to DTO
        ReviewDTO result = new ReviewDTO();
        result.setId(saved.getId());
        result.setRating(saved.getRating());
        result.setComment(saved.getComment());
        result.setCreatedAt(saved.getCreatedAt());
        // Set user info
        result.setUser(reviewDTO.getUser());
        return result;
    }

    @Transactional
    @Override
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO, CustomUserDetails user) {
        Optional<Reviews> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isEmpty()) {
            throw new RuntimeException("Review not found");
        }
        Reviews review = reviewOpt.get();
        // Optional: check if the user is the owner of the review
        if (reviewDTO.getUser() == null || reviewDTO.getUser().getId() == null ||
            review.getUser() == null || !review.getUser().getId().equals(reviewDTO.getUser().getId())) {
            throw new RuntimeException("You are not allowed to update this review");
        }
        // Update fields
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        Reviews saved = reviewRepository.save(review);
        // Convert back to DTO
        ReviewDTO result = new ReviewDTO();
        result.setId(saved.getId());
        result.setRating(saved.getRating());
        result.setComment(saved.getComment());
        result.setCreatedAt(saved.getCreatedAt());
        result.setUser(reviewDTO.getUser());
        return result;
    }

    @Transactional
    @Override
    public void deleteReviewById(int id, CustomUserDetails user) {
        Optional<Reviews> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isEmpty()) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}

