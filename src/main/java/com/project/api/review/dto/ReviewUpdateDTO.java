package com.project.api.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReviewUpdateDTO {
    private Long reviewId;
    private String title;
    private String description;
    private String image;
}
