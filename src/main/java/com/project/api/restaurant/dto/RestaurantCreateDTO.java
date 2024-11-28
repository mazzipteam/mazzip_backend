package com.project.api.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RestaurantCreateDTO {
    private String name;
    private String address;
    private String businessName;
    private String propritor;
    private String category;
    private String telNum;
    private Character takeOut;
    private Long userId;
}
