package com.project.api.review;

import com.project.api.review.dto.AnswerDTO;
import com.project.api.review.dto.ReviewCreateDTO;
import com.project.api.review.dto.ReviewUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create(
            @RequestPart ReviewCreateDTO reviewCreateDTO,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        var review = reviewService.create(reviewCreateDTO, image);
        var response = CommonResponse.builder().code(200).message("리뷰 생성 성공").data(review).build();
        return ResponseEntity.ok(response);
    }

    // 리뷰 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        var review = reviewService.update(reviewUpdateDTO);
        var response = CommonResponse.builder().code(200).message("리뷰 수정 성공").data(review).build();
        return ResponseEntity.ok(response);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity delete(@PathVariable Long reviewId) {
        var review = reviewService.delete(reviewId);
        var response = CommonResponse.builder().code(200).message("리뷰 삭제 성공").data(review).build();
        return ResponseEntity.ok(response);
    }

    // 리뷰 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity get(@PathVariable Long reviewId) {
        var review = reviewService.getReview(reviewId);
        var response = CommonResponse.builder().code(200).message("리뷰 조회 성공").data(review).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity getAllByUser(@PathVariable Long userId) {
        var reviews = reviewService.getAllReportByUser(userId);
        var response = CommonResponse.builder().code(200).message("리뷰 조회 성공").data(reviews).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/user/{restaurantId}")
    public ResponseEntity getAllByRestaurant(@PathVariable Long restaurantId) {
        var reviews = reviewService.getAllReportByRestaurant(restaurantId);
        var response = CommonResponse.builder().code(200).message("리뷰 조회 성공").data(reviews).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/add/rating/{restaurantId}")
    public ResponseEntity getRatingByRestaurant(@PathVariable Long restaurantId) {
        var reviews = reviewService.getAllReportByRestaurant(restaurantId);
        var ratings = reviewService.getRatings(reviews);
        var response = CommonResponse.builder().code(200).message("평점 조회 성공").data(ratings).build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/answer")
    public ResponseEntity answer(@RequestBody AnswerDTO answerDTO) {
        var reviews = reviewService.answer(answerDTO);
        var response = CommonResponse.builder().code(200).message("답변 작성 완료").data(reviews).build();
        return ResponseEntity.ok(response);
    }
}
