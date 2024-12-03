package com.project.api.clothes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClothesCreateDTO {
    private String name;
    private String category;
    private Integer cost;
}
