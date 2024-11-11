package com.project.api.retaurant;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    // 맛집 생성
    @PostMapping
    public ResponseEntity create(@RequestBody RestaurantCreateDTO restaurantCreateDTO) {
        var user = restaurantService.create(restaurantCreateDTO);
        var response = CommonResponse.builder().code(200).message("유저 생성 성공").data(user).build();
        return ResponseEntity.ok(response);
    }
    
    // 맛집 수정
    
    // 맛집 삭제
    
    // 맛집 조회
}
