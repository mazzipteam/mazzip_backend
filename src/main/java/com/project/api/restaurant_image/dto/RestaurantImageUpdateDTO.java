package com.project.api.restaurant_image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestaurantImageUpdateDTO {
    private Long restaurantImageId;
    private String foreGround;
    private String interior;
    private String menu;
}
