package com.project.api.menu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MenuCreateDTO {
    private Long restaurantId;
    private Long foodCategoryId;
    private String name;
    private Integer price;
    private String description;
    private Character cheap;
    private byte[] image;
    private Character main;
    private Character isReserve;
}
