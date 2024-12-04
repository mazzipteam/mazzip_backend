package com.project.api.signup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignUpDTO {
    private Long userId;
    private String email;
    private String password;
    private String nickName;
    private String telNum;
    private String address;
    private String detailAddress;
    private String role;
    private String name;
    private String restaurantAddress;
    private String businessName;
    private String propritor;
    private String category;
    private String restaurantTelNum;
    private String takeOut;
}
