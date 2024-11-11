package com.project.api.retaurant;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    // 맛집 생성
    @PostMapping
    public ResponseEntity create(@RequestBody RestaurantCreateDTO restaurantCreateDTO) {
        var restaurant = restaurantService.create(restaurantCreateDTO);
        var response = CommonResponse.builder().code(200).message("맛집 생성 성공").data(restaurant).build();
        return ResponseEntity.ok(response);
    }
    
    // 맛집 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody RestaurantUpdateDTO restaurantUpdateDTO) {
        var restaurant = restaurantService.update(restaurantUpdateDTO);
        var response = CommonResponse.builder().code(200).message("맛집 수정 성공").data(restaurant).build();
        return ResponseEntity.ok(response);
    }
    
    // 맛집 삭제
    
    // 맛집 조회
}
