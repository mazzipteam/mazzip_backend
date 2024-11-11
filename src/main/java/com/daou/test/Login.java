package com.daou.test;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Login {
    private String id;
    private String password;
}
