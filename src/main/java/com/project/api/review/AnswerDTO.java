package com.project.api.review;

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
