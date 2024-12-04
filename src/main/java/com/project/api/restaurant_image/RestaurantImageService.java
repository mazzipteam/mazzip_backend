package com.project.api.restaurant_image;

import com.project.api.restaurant_image.dto.RestaurantImageUpdateDTO;
import com.project.entity.RestaurantImage;
import com.project.entity.restraunt.Restaurant;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.project.exception.error_code.RestaurantImageErrorMessage.RESTAURANT_IMAGE_ALREADY_EXISTS;
import static com.project.exception.error_code.RestaurantImageErrorMessage.RESTAURANT_IMAGE_NOT_FOUND;
import static com.project.exception.error_code.ClothesErrorCode.IMAGE_OF_INCORRECT_FORMAT;

@Service
@RequiredArgsConstructor
public class RestaurantImageService {
    private final RestaurantImageRepository restaurantImageRepository;

    public RestaurantImage create(Restaurant restaurant, MultipartFile multipartFileForeground, MultipartFile multipartFileInterior, MultipartFile multipartFileMenu) {
        if(restaurantImageRepository.findByRestaurant(restaurant).isPresent())
            throw new ControlledException(RESTAURANT_IMAGE_ALREADY_EXISTS);

        try {
            var restaurantImage = RestaurantImage.builder()
                    .restaurant(restaurant)
                    .foreGround(multipartFileForeground.getBytes())
                    .interior(multipartFileInterior.getBytes())
                    .menu(multipartFileMenu.getBytes())
                    .build();

            restaurantImageRepository.save(restaurantImage);
            return restaurantImage;
        } catch (Exception ex) {
            throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
        }
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

        restaurantImageRepository.deleteById(restaurantImageId);
        return restaurantImage;
    }

    public RestaurantImage getRestaurantImage(Long restaurantImageId) {
        var restaurantImage = restaurantImageRepository.findByRestaurantImageId(restaurantImageId)
                .orElseThrow(()->new ControlledException(RESTAURANT_IMAGE_NOT_FOUND));

        return restaurantImage;
    }
}
