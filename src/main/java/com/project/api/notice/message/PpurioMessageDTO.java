package com.project.api.notice.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PpurioMessageDTO {
    private Long restaurantId;
    private String content;
}