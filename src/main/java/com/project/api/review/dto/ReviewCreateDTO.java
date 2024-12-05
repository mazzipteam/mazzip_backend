package com.project.api.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReviewCreateDTO {
    private Double rating;
    private String title;
    private String description;
    private Long userId;
    private Long restaurantId;
    private int recommend;
}
