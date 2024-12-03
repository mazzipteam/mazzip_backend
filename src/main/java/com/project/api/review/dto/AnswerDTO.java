package com.project.api.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AnswerDTO {
    Long reviewId;
    String answer;
}
