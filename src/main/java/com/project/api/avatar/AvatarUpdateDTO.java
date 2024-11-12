package com.project.api.avatar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AvatarUpdateDTO {
    private Long avatarId;
    private String name;
}
