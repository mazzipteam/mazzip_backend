package com.project.api.memo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MemoUpdateDTO {
    private Long memoId;
    private String title;
    private String description;
}
