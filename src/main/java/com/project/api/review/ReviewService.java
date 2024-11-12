package com.project.api.review;

import com.project.api.restaurant.RestaurantService;
import com.project.api.review.dto.ReviewCreateDTO;
import com.project.api.review.dto.ReviewUpdateDTO;
import com.project.api.user.UserService;
import com.project.entity.Review;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.project.exception.error_code.ReviewErrorCode.IMAGE_OF_INCORRECT_FORMAT;
import static com.project.exception.error_code.ReviewErrorCode.REVIEW_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final ReviewRepository reviewRepository;

    public Review create(ReviewCreateDTO reviewCreateDTO) {
        var user = userService.getUser(reviewCreateDTO.getUserId());
        var restaurant = restaurantService.getRestaurant(reviewCreateDTO.getRestaurantId());

        var review = Review.builder()
                .rating(reviewCreateDTO.getRating())
                .title(reviewCreateDTO.getTitle())
                .description(reviewCreateDTO.getDescription())
                .image(reviewCreateDTO.getImage())
                .user(user)
                .restaurant(restaurant)
                .build();

        reviewRepository.save(review);
        return review;
    }

    public Review update(ReviewUpdateDTO reviewUpdateDTO) {
        var review = reviewRepository.findByReviewId(reviewUpdateDTO.getReviewId())
                .orElseThrow(()->new ControlledException(REVIEW_NOT_FOUND));

        if(!reviewUpdateDTO.getTitle().isEmpty())
            review.setTitle(reviewUpdateDTO.getTitle());

        if(!reviewUpdateDTO.getDescription().isEmpty())
            review.setDescription(reviewUpdateDTO.getDescription());

        if(!reviewUpdateDTO.getImage().isEmpty()){
            // 1. [예외처리] 잘못된 형식의 이미지
            try {
                review.setImage(reviewUpdateDTO.getImage().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }
        review.setUpdatedAt(LocalDateTime.now());

        reviewRepository.save(review);
        return review;
    }

    public Review delete(Long reviewId) {
        var review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(()->new ControlledException(REVIEW_NOT_FOUND));

        reviewRepository.deleteByReviewId(reviewId);
        return review;
    }

    public Review getReview(Long reviewId) {
        var review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(()->new ControlledException(REVIEW_NOT_FOUND));

        return review;
    }
}
