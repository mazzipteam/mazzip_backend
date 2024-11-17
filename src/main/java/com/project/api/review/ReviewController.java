package com.project.api.review;

import com.project.api.review.dto.ReviewCreateDTO;
import com.project.api.review.dto.ReviewUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity create(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        var review = reviewService.create(reviewCreateDTO);
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

    // TODO: 유저 기준 신고

    // TODO: 식당 기준 신고 수
}
