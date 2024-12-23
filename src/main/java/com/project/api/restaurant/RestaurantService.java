package com.project.api.restaurant;

import com.project.api.restaurant.dto.RestaurantCreateDTO;
import com.project.api.restaurant.dto.RestaurantUpdateDTO;
import com.project.entity.restraunt.Category;
import com.project.entity.restraunt.Restaurant;
import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.entity.restraunt.Region.성북구;
import static com.project.exception.error_code.RestaurantErrorCode.*;
import static com.project.exception.error_code.UserErrorCode.TELNUM_OF_INCORRECT_FORMAT;
import static com.project.api.user.UserService.isValidTelNum;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    public Restaurant create(RestaurantCreateDTO restaurantCreateDTO) {
        // 1. [예외처리] 전화번호 서식 오류
        if(!isValidTelNum(restaurantCreateDTO.getTelNum()))
            throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);

        // 2. [예외처리] 잘못된 형식의 카테고리
        Category category;
        try {
            category = Category.valueOf(restaurantCreateDTO.getCategory());
        } catch (IllegalArgumentException e) {
            throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
        }

        //TODO: region이 업데이트 되는 경우, region에 대한 예외처리 필요

        // 3. [예외처리] 이미 존재하는 맛집명
        if(restaurantRepository.findByName(restaurantCreateDTO.getName()).isPresent())
            throw new ControlledException(RESTAURANT_NAME_ALREADY_EXISTS);

        var user = userService.getUser(restaurantCreateDTO.getUserId());

        var restaurant = Restaurant.builder()
                .name(restaurantCreateDTO.getName())
                .address(restaurantCreateDTO.getAddress())
                .businessName(restaurantCreateDTO.getBusinessName())
                .propritor(restaurantCreateDTO.getPropritor())
                .category(category)
                .region(성북구)
                .telNum(restaurantCreateDTO.getTelNum())
                .takeOut(restaurantCreateDTO.getTakeOut())
                .rating(0.0)
                .user(user)
                .build();

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant update(RestaurantUpdateDTO restaurantUpdateDTO) {
        // 1. [예외처리] 전화번호 서식 오류
        if(!isValidTelNum(restaurantUpdateDTO.getTelNum()))
            throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);

        // 2. [예외처리] 잘못된 형식의 카테고리
        Category category;
        try {
            category = Category.valueOf(restaurantUpdateDTO.getCategory());
        } catch (IllegalArgumentException e) {
            throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
        }

        // 3. [예외처리] 이미 존재하는 맛집명
        if(restaurantRepository.findByName(restaurantUpdateDTO.getName()).isPresent())
            throw new ControlledException(RESTAURANT_NAME_ALREADY_EXISTS);

        if(restaurantUpdateDTO.getTakeOut()!= null
                && !restaurantUpdateDTO.getTakeOut().equals("Y")
                && !restaurantUpdateDTO.getTakeOut().equals("N"))
            throw new ControlledException(TAKE_OUT_OF_INCORRECT_FORMAT);

        var restaurant = getRestaurant(restaurantUpdateDTO.getRestaurantId());

        if(restaurantUpdateDTO.getName()!= null)
            restaurant.setName(restaurantUpdateDTO.getName());

        if(restaurantUpdateDTO.getAddress()!= null)
            restaurant.setName(restaurantUpdateDTO.getAddress());

        if(restaurantUpdateDTO.getBusinessName()!= null)
            restaurant.setName(restaurantUpdateDTO.getBusinessName());

        if(restaurantUpdateDTO.getPropritor()!= null)
            restaurant.setName(restaurantUpdateDTO.getPropritor());

        restaurant.setCategory(category);

        if(restaurantUpdateDTO.getTelNum()!= null)
            restaurant.setName(restaurantUpdateDTO.getTelNum());

        if(restaurantUpdateDTO.getTakeOut()!= null)
            restaurant.setName(restaurantUpdateDTO.getTakeOut());

        if(restaurantUpdateDTO.getUserId()!= null){
            var userId = Long.parseLong(restaurantUpdateDTO.getUserId());
            var user = userService.getUser(userId);
            restaurant.setUser(user);
        }

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant delete(Long restaurantId) {
        var restaurant = getRestaurant(restaurantId);

        restaurantRepository.deleteById(restaurantId);
        return restaurant;
    }

    public Restaurant getRestaurant(Long restaurantId) {
        var restaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new ControlledException(RESTAURANT_NOT_FOUND));

        return restaurant;
    }

    public List<Restaurant> getRestaurantAll() {
        var restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurantByUser(Long userId) {
        var user = userService.getUser(userId);
        var restaurant = restaurantRepository.findByUser(user)
                .orElseThrow(()->new ControlledException(RESTAURANT_NOT_FOUND_BY_USER));

        return restaurant;
    }
}
