package com.project.api.restaurant;

import com.project.api.restaurant.dto.RestaurantCreateDTO;
import com.project.api.restaurant.dto.RestaurantUpdateDTO;
import com.project.api.restaurant_image.RestaurantImageService;
import com.project.common.CommonResponse;
import com.project.common.OctetStreamReadMsgConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantImageService restaurantImageService;

    // TODO: Category 반환하는 API도 있어야 할듯
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create(
            @RequestPart RestaurantCreateDTO restaurantCreateDTO,
            @RequestPart(value = "multipartFileForeground", required = false) MultipartFile multipartFileForeground,
            @RequestPart(value = "multipartFileInterior", required = false) MultipartFile multipartFileInterior,
            @RequestPart(value = "multipartFileMenu", required = false) MultipartFile multipartFileMenu) {

        var restaurant = restaurantService.create(restaurantCreateDTO);

        // 식당 이미지 추가
        restaurantImageService.create(restaurant, multipartFileForeground, multipartFileInterior, multipartFileMenu);

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
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity delete(@RequestParam Long restaurantId) {
        var restaurant = restaurantService.delete(restaurantId);
        var response = CommonResponse.builder().code(200).message("맛집 삭제 성공").data(restaurant).build();
        return ResponseEntity.ok(response);
    }
    
    // 맛집 조회
    @GetMapping("/{restaurantId}")
    public ResponseEntity get(@PathVariable Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var response = CommonResponse.builder().code(200).message("유저 조회 성공").data(restaurant).build();
        return ResponseEntity.ok(response);
    }
}
