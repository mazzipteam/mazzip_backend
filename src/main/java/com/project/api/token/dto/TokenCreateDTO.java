package com.project.api.token.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenCreateDTO {
    private String fcmToken;
    private Long userId;
}
