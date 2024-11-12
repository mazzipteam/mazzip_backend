package com.project.api.bookmark;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookmarkCreateDTO {
    private Long userId;
    private Long restaurantId;
}
