package com.project.api.my_clothes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MyClothesCreateDTO {
    private Long avatarId;
    private Long clothesId;
}
