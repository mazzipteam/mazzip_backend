package com.project.api.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class LoginDTO {
    String email;
    String password;
}
