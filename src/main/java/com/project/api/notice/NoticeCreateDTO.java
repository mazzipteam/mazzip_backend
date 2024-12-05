package com.project.api.notice;

import com.project.entity.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class NoticeCreateDTO {
    private Bookmark bookmark;
    private String message;
}
