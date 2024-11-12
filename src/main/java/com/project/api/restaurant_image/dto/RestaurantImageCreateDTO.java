package com.project.api.restaurant_image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestaurantImageCreateDTO {
    private Long restaurantId;
    private byte[] foreGround;
    private byte[] interior;
    private byte[] menu;
}
