package com.project.api.clothes;

import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.api.clothes.ClothesErrorCode.*;

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
        var clothes = clothesRepository.findByClothesId(clothesUpdateDTO.getClothesId())
                .orElseThrow(() -> new ControlledException(CLOTHES_NOT_FOUND));

        if(!clothesUpdateDTO.getName().isEmpty()) {
            // 1. [예외처리] 이미 존재하는 의상명
            if(clothesRepository.findByName(clothesUpdateDTO.getName()).isPresent())
                throw new ControlledException(CLOTHES_NAME_ALREADY_EXISTS);

            clothes.setName(clothesUpdateDTO.getName());
        }

        if(!clothesUpdateDTO.getCategory().isEmpty()) {
            // 2. [예외처리] 잘못된 형식의 카테고리
            Category category;
            try {
                category = Category.valueOf(clothesUpdateDTO.getCategory());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
            }

            clothes.setCategory(category);
        }

        if(!clothesUpdateDTO.getImage().isEmpty())
            clothes.setImage(clothesUpdateDTO.getImage().getBytes());
        
        if(!clothesUpdateDTO.getCost().isEmpty()) {
            // TODO: 반환받은 문자열이 숫자인지 예외처리
            clothes.setCost(Integer.valueOf(clothesUpdateDTO.getCost()));
        }

        clothesRepository.save(clothes);
        return clothes;
    }

    public Clothes delete(Long clothesId) {
        var clothes = clothesRepository.findById(clothesId)
                        .orElseThrow(()->new ControlledException(CLOTHES_NOT_FOUND));

        clothesRepository.deleteByClothesId(clothesId);
        return clothes;
    }

    public Clothes getClothes(Long clothesId) {
        var clothes = clothesRepository.findById(clothesId)
                .orElseThrow(()->new ControlledException(CLOTHES_NOT_FOUND));

        return clothes;
    }
}
