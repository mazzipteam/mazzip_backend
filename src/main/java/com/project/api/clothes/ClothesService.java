package com.project.api.clothes;

import com.project.api.clothes.dto.ClothesCreateDTO;
import com.project.api.clothes.dto.ClothesUpdateDTO;
import com.project.entity.clothes.Category;
import com.project.entity.clothes.Clothes;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.exception.error_code.ClothesErrorCode.*;

@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public Clothes create(ClothesCreateDTO clothesCreateDTO) {
        // 1. [예외처리] 잘못된 형식의 카테고리
        Category category;
        try {
            category = Category.valueOf(clothesCreateDTO.getCategory());
        } catch (IllegalArgumentException e) {
            throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
        }

        var clothes = Clothes.builder()
                .name(clothesCreateDTO.getName())
                .category(category)
                .image(clothesCreateDTO.getImage())
                .cost(clothesCreateDTO.getCost())
                .build();

        clothesRepository.save(clothes);
        return clothes;
    }

    public Clothes update(ClothesUpdateDTO clothesUpdateDTO) {
        var clothes = getClothes(clothesUpdateDTO.getClothesId());

        if(clothesUpdateDTO.getName()!= null) {
            // 1. [예외처리] 이미 존재하는 의상명
            if(clothesRepository.findByName(clothesUpdateDTO.getName()).isPresent())
                throw new ControlledException(CLOTHES_NAME_ALREADY_EXISTS);

            clothes.setName(clothesUpdateDTO.getName());
        }

        if(clothesUpdateDTO.getCategory()!= null) {
            // 2. [예외처리] 잘못된 형식의 카테고리
            Category category;
            try {
                category = Category.valueOf(clothesUpdateDTO.getCategory());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
            }

            clothes.setCategory(category);
        }

        if(clothesUpdateDTO.getImage()!= null){
            try {
                clothes.setImage(clothesUpdateDTO.getImage().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }
        
        if(clothesUpdateDTO.getCost()!= null) {
            // 3. [예외처리] 숫자가 아닌 문자열 반환
            try {
                clothes.setCost(Integer.valueOf(clothesUpdateDTO.getCost()));
            } catch (NumberFormatException e) {
                throw new ControlledException(COST_OF_INCORRECT_FORMAT);
            }
        }

        clothesRepository.save(clothes);
        return clothes;
    }

    public Clothes delete(Long clothesId) {
        var clothes = getClothes(clothesId);

        clothesRepository.deleteByClothesId(clothesId);
        return clothes;
    }

    public Clothes getClothes(Long clothesId) {
        var clothes = clothesRepository.findById(clothesId)
                .orElseThrow(()->new ControlledException(CLOTHES_NOT_FOUND));

        return clothes;
    }
}
