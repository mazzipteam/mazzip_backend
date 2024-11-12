package com.project.api.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokenUpdateDTO {
    private Long tokenId;
    private String fcmToken;
}
