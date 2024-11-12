package com.project.api.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeCreateDTO {
    private Long userId;
    private Long bookmarkId;
    private String message;
}
