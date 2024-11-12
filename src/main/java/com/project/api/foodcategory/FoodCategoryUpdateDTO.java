package com.project.api.foodcategory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FoodCategoryUpdateDTO {
    private Long foodCategoryId;
    private String name;
}
