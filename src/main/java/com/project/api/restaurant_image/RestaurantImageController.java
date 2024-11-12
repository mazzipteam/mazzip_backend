package com.project.api.restaurant_image;

import com.project.api.restaurant_image.dto.RestaurantImageCreateDTO;
import com.project.api.restaurant_image.dto.RestaurantImageUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantImageController {
    private final RestaurantImageService restaurantImageService;

    // 맛집 생성
    @PostMapping
    public ResponseEntity create(@RequestBody RestaurantImageCreateDTO restaurantImageCreateDTO) {
        var restaurantImage = restaurantImageService.create(restaurantImageCreateDTO);
        var response = CommonResponse.builder().code(200).message("맛집 생성 성공").data(restaurantImage).build();
        return ResponseEntity.ok(response);
    }

    // 맛집 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody RestaurantImageUpdateDTO restaurantImageUpdateDTO) {
        var restaurantImage = restaurantImageService.update(restaurantImageUpdateDTO);
        var response = CommonResponse.builder().code(200).message("맛집 수정 성공").data(restaurantImage).build();
        return ResponseEntity.ok(response);
    }

    // 맛집 삭제
    @DeleteMapping("/{restaurantImageId}")
    public ResponseEntity delete(@RequestParam Long restaurantImageId) {
        var restaurantImage = restaurantImageService.delete(restaurantImageId);
        var response = CommonResponse.builder().code(200).message("맛집 삭제 성공").data(restaurantImage).build();
        return ResponseEntity.ok(response);
    }

    // 맛집 조회
    @GetMapping("/{restaurantImageId}")
    public ResponseEntity get(@PathVariable Long restaurantImageId) {
        var restaurantImage = restaurantImageService.getRestaurantImage(restaurantImageId);
        var response = CommonResponse.builder().code(200).message("유저 조회 성공").data(restaurantImage).build();
        return ResponseEntity.ok(response);
    }
}
