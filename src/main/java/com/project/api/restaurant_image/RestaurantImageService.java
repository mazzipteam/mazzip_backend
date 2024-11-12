package com.project.api.restaurant_image;

import com.project.api.restaurant.RestaurantService;
import com.project.api.restaurant_image.dto.RestaurantImageCreateDTO;
import com.project.api.restaurant_image.dto.RestaurantImageUpdateDTO;
import com.project.entity.RestaurantImage;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.exception.error_code.RestaurantImageErrorMessage.RESTAURANT_IMAGE_ALREADY_EXISTS;
import static com.project.exception.error_code.RestaurantImageErrorMessage.RESTAURANT_IMAGE_NOT_FOUND;
import static com.project.exception.error_code.ClothesErrorCode.IMAGE_OF_INCORRECT_FORMAT;

@Service
@RequiredArgsConstructor
public class RestaurantImageService {
    private final RestaurantImageRepository restaurantImageRepository;
    private final RestaurantService restaurantService;

    public RestaurantImage create(RestaurantImageCreateDTO restaurantImageCreateDTO) {
        // 1. [예외처리] 이미 식당에 이미지가 존재하는 경우
        if(restaurantImageRepository.findByRestaurantId(restaurantImageCreateDTO.getRestaurantId()).isPresent())
            throw new ControlledException(RESTAURANT_IMAGE_ALREADY_EXISTS);

        var restaurant = restaurantService.getRestaurant(restaurantImageCreateDTO.getRestaurantId());

        var restaurantImage = RestaurantImage.builder()
                .restaurant(restaurant)
                .foreGround(restaurantImageCreateDTO.getForeGround())
                .interior(restaurantImageCreateDTO.getInterior())
                .menu(restaurantImageCreateDTO.getMenu())
                .build();

        restaurantImageRepository.save(restaurantImage);
        return restaurantImage;
    }

    public RestaurantImage update(RestaurantImageUpdateDTO restaurantImageUpdateDTO) {
        var restaurantImage = getRestaurantImage(restaurantImageUpdateDTO.getRestaurantImageId());

        if(restaurantImageUpdateDTO.getForeGround()!= null){
            try {
                restaurantImage.setForeGround(restaurantImageUpdateDTO.getForeGround().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }

        if(restaurantImageUpdateDTO.getInterior()!= null){
            try {
                restaurantImage.setInterior(restaurantImageUpdateDTO.getInterior().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }

        if(restaurantImageUpdateDTO.getMenu()!= null){
            try {
                restaurantImage.setMenu(restaurantImageUpdateDTO.getMenu().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }

        restaurantImageRepository.save(restaurantImage);
        return restaurantImage;
    }

    public RestaurantImage delete(Long restaurantImageId) {
        var restaurantImage = getRestaurantImage(restaurantImageId);

        restaurantImageRepository.deleteByRestaurantImageId(restaurantImageId);
        return restaurantImage;
    }

    public RestaurantImage getRestaurantImage(Long restaurantImageId) {
        var restaurantImage = restaurantImageRepository.findByRestaurantImageId(restaurantImageId)
                .orElseThrow(()->new ControlledException(RESTAURANT_IMAGE_NOT_FOUND));

        return restaurantImage;
    }
}
