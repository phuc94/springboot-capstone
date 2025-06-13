package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.dto.mapper.ReviewMapper;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.ReviewRepository;
import com.cybersoft.capstone.service.interfaces.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
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

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        return reviewMapper.toReviewDTO(reviewRepository.save(reviewMapper.toReviews(reviewDTO)));
    }

    @Override
    public ReviewDTO updateReview(int id, ReviewDTO reviewDTO) {
        return reviewRepository.findById(id)
                .map(foundReview -> {
                    foundReview.setRating(reviewDTO.getRating());
                    foundReview.setComment(reviewDTO.getComment());
                    return reviewMapper.toReviewDTO(reviewRepository.save(foundReview));
                })
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @Override
    public void deleteReviewById(int id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
