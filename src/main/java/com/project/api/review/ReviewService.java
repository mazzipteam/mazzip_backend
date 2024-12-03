package com.project.api.review;

import com.project.api.restaurant.RestaurantService;
import com.project.api.review.dto.AnswerDTO;
import com.project.api.review.dto.ReviewCreateDTO;
import com.project.api.review.dto.ReviewUpdateDTO;
import com.project.api.user.UserService;
import com.project.entity.Review;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.project.api.report.ReportErrorMessage.REPORT_NOT_FOUND_BY_RESTAURANT;
import static com.project.api.report.ReportErrorMessage.REPORT_NOT_FOUND_BY_USER;
import static com.project.exception.error_code.ReviewErrorCode.IMAGE_OF_INCORRECT_FORMAT;
import static com.project.exception.error_code.ReviewErrorCode.REVIEW_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final ReviewRepository reviewRepository;

    public Review create(ReviewCreateDTO reviewCreateDTO, MultipartFile image) {
        var user = userService.getUser(reviewCreateDTO.getUserId());
        var restaurant = restaurantService.getRestaurant(reviewCreateDTO.getRestaurantId());

        try {
            var review = Review.builder()
                    .rating(reviewCreateDTO.getRating())
                    .title(reviewCreateDTO.getTitle())
                    .description(reviewCreateDTO.getDescription())
                    .image(image.getBytes())
                    .user(user)
                    .restaurant(restaurant)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .recommend(0)
                    .build();

            reviewRepository.save(review);
            return review;
        } catch (IOException e) {
            throw new ControlledException(REVIEW_NOT_FOUND);
        }
    }

    public Review update(ReviewUpdateDTO reviewUpdateDTO) {
        var review = getReview(reviewUpdateDTO.getReviewId());

        if(reviewUpdateDTO.getTitle()!= null)
            review.setTitle(reviewUpdateDTO.getTitle());

        if(reviewUpdateDTO.getDescription()!= null)
            review.setDescription(reviewUpdateDTO.getDescription());

        if(reviewUpdateDTO.getImage()!= null){
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
        var review = getReview(reviewId);

        reviewRepository.deleteByReviewId(reviewId);
        return review;
    }

    public Review getReview(Long reviewId) {
        var review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(()->new ControlledException(REVIEW_NOT_FOUND));

        return review;
    }

    public List<Review> getAllReportByUser(Long userId) {
        var user = userService.getUser(userId);
        var reviews = reviewRepository.findByUser(user)
                .orElseThrow(()->new ControlledException(REPORT_NOT_FOUND_BY_USER));

        return reviews;
    }

    public List<Review> getAllReportByRestaurant(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var reviews = reviewRepository.findByRestaurant(restaurant)
                .orElseThrow(()->new ControlledException(REPORT_NOT_FOUND_BY_RESTAURANT));

        return reviews;
    }

    public List<Double> getRatings(List<Review> reviews) {
        List<Double> counters = new ArrayList<>(0);
        for (Review review : reviews) {
            counters.set(review.getRating().intValue(), counters.get(review.getRating().intValue())+1);
            counters.set(0, counters.getFirst()+review.getRating());
        }

        return counters;
    }

    public Review answer(AnswerDTO answerDTO) {
        var review = getReview(answerDTO.getReviewId());
        review.setAnswer(answerDTO.getAnswer());

        reviewRepository.save(review);
        return review;
    }
}
