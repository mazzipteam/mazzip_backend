package com.project.api.menu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MenuUpdateDTO {
    private Long menuId;
    private String foodCategoryId;
    private String name;
    private String price;
    private String description;
    private String cheap;
    private String main;
    private String isReserve;
}
