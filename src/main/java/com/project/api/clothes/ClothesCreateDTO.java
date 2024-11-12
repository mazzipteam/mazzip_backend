package com.project.api.clothes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClothesCreateDTO {
    private String name;
    private String category;
    private byte[] image;
    private Integer cost;
}
