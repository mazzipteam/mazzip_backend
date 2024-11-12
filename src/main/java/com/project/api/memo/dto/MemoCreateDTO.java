package com.project.api.memo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MemoCreateDTO {
    private String title;
    private String description;
    private Long userId;
    private Long restaurantId;
}
