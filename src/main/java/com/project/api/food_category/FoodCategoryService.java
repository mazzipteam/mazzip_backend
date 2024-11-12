package com.project.api.food_category;

import com.project.api.food_category.dto.FoodCategoryCreateDTO;
import com.project.api.food_category.dto.FoodCategoryUpdateDTO;
import com.project.entity.FoodCategory;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.exception.error_code.FoodCategoryErrorCode.FOOD_CATEGORY_NAME_ALREADY_EXISTS;
import static com.project.exception.error_code.FoodCategoryErrorCode.FOOD_CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategory create(FoodCategoryCreateDTO foodCategoryCreateDTO) {
        // 1. [예외처리] 이미 존재하는 음식 카테고리명
        if(foodCategoryRepository.findByName(foodCategoryCreateDTO.getName()).isEmpty())
            throw new ControlledException(FOOD_CATEGORY_NAME_ALREADY_EXISTS);

        var foodCategory = FoodCategory.builder()
                .name(foodCategoryCreateDTO.getName())
                .build();

        foodCategoryRepository.save(foodCategory);
        return foodCategory;
    }

    public FoodCategory update(FoodCategoryUpdateDTO foodCategoryUpdateDTO) {
        // 1. [예외처리] 이미 존재하는 음식 카테고리명
        if(foodCategoryRepository.findByName(foodCategoryUpdateDTO.getName()).isEmpty())
            throw new ControlledException(FOOD_CATEGORY_NAME_ALREADY_EXISTS);

        var foodCategory = foodCategoryRepository.findByFoodCategoryId(foodCategoryUpdateDTO.getFoodCategoryId())
                .orElseThrow(() -> new ControlledException(FOOD_CATEGORY_NOT_FOUND));

        if(foodCategoryUpdateDTO.getName() != null)
            foodCategory.setName(foodCategoryUpdateDTO.getName());

        foodCategoryRepository.save(foodCategory);
        return foodCategory;
    }

    public FoodCategory delete(Long foodCategoryId) {
        var foodCategory = foodCategoryRepository.findById(foodCategoryId)
                .orElseThrow(() -> new ControlledException(FOOD_CATEGORY_NOT_FOUND));

        foodCategoryRepository.deleteByFoodCategoryId(foodCategoryId);
        return foodCategory;
    }

    public FoodCategory getFoodCategory(Long foodCategoryId) {
        var foodCategory = foodCategoryRepository.findById(foodCategoryId)
                .orElseThrow(() -> new ControlledException(FOOD_CATEGORY_NOT_FOUND));

        return foodCategory;
    }
}
