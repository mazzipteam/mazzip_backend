package com.project.api.clothes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClothesUpdateDTO {
    private Long clothesId;
    private String name;
    private String category;
    private String image;
    private String cost;
}
