package com.cybersoft.capstone.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersoft.capstone.dto.ReviewDTO;
import com.cybersoft.capstone.dto.mapper.ReviewMapper;
import com.cybersoft.capstone.entity.Games;
import com.cybersoft.capstone.entity.OrderItem;
import com.cybersoft.capstone.exception.NotFoundException;
import com.cybersoft.capstone.repository.GameRepository;
import com.cybersoft.capstone.repository.ReviewRepository;
import com.cybersoft.capstone.service.interfaces.OrderItemService;
import com.cybersoft.capstone.service.interfaces.ReviewService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final OrderItemService orderItemService;
    private final GameRepository gameRepository;

    public ReviewServiceImpl(
        ReviewRepository reviewRepository,
        ReviewMapper reviewMapper,
        OrderItemService orderItemService,
        GameRepository gameRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.orderItemService = orderItemService;
        this.gameRepository = gameRepository;
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
    @Transactional
    public ReviewDTO createClientReview(ReviewDTO reviewDTO, int orderId) {
        Boolean alreadyReviewed = reviewRepository.existsByUserIdAndGameId(reviewDTO.getUser().getId(), reviewDTO.getGameId());
        if (!alreadyReviewed) {

            // Update OrderItem review status
            OrderItem orderItem = orderItemService.findByOrderIdAndGameId(orderId, reviewDTO.getGameId());
            orderItem.setReviewed(true);
            orderItemService.save(orderItem);

            // Update game avg rating and rating count
            Games game = gameRepository.findById(reviewDTO.getGameId())
                    .orElseThrow(()-> new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase()));
            if (game.getRatingCount() == 0) {
                game.setAvgRating(reviewDTO.getRating());
                game.setRatingCount(1);
            } else {
                int newAvgRating = (game.getAvgRating() * game.getRatingCount() + reviewDTO.getRating()) / (game.getRatingCount() + 1);
                game.setAvgRating(newAvgRating);
                game.setRatingCount(game.getRatingCount() + 1);
                gameRepository.save(game);
            }

            // Add new review row
            return reviewMapper.toReviewDTO(reviewRepository.save(reviewMapper.toReviews(reviewDTO)));
        }
        throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());
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
