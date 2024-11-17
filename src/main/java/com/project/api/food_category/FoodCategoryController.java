package com.project.api.food_category;

import com.project.api.food_category.dto.FoodCategoryCreateDTO;
import com.project.api.food_category.dto.FoodCategoryUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/foodCategory")
@RequiredArgsConstructor
public class FoodCategoryController {
    private final FoodCategoryService foodCategoryService;

    // 음식 카테고리 생성
    @PostMapping
    public ResponseEntity create(@RequestBody FoodCategoryCreateDTO foodCategoryCreateDTO) {
        var foodCategory = foodCategoryService.create(foodCategoryCreateDTO);
        var response = CommonResponse.builder().code(200).message("음식 카테고리 생성 성공").data(foodCategory).build();
        return ResponseEntity.ok(response);
    }
    
    // 음식 카테고리 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody FoodCategoryUpdateDTO foodCategoryUpdateDTO) {
        var foodCategory = foodCategoryService.update(foodCategoryUpdateDTO);
        var response = CommonResponse.builder().code(200).message("음식 카테고리 수정 성공").data(foodCategory).build();
        return ResponseEntity.ok(response);
    }

    // 음식 카테고리 삭제
    @DeleteMapping("/{foodCategoryId}")
    public ResponseEntity delete(@PathVariable Long foodCategoryId) {
        var foodCategory = foodCategoryService.delete(foodCategoryId);
        var response = CommonResponse.builder().code(200).message("음식 카테고리 삭제 성공").data(foodCategory).build();
        return ResponseEntity.ok(response);
    }

    // 음식 카테고리 조회
    @GetMapping("/{foodCategoryId}")
    public ResponseEntity get(@PathVariable Long foodCategoryId) {
        var foodCategory = foodCategoryService.getFoodCategory(foodCategoryId);
        var response = CommonResponse.builder().code(200).message("음식 카테고리 조회 성공").data(foodCategory).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        var foodCategories = foodCategoryService.getAllFoodCategoty();
        var response = CommonResponse.builder().code(200).message("음식 카테고리 조회 성공").data(foodCategories).build();
        return ResponseEntity.ok(response);
    }
}
