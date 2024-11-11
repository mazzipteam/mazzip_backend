package com.project.api.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserCreateDTO {
    private String email;
    private String password;
    private String nickName;
    private String telNum;
    private String address;
    private String detailAddress;
    private String role = "USER";
}

