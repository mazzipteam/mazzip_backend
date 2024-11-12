package com.project.api.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TestAccessDTO {
    private String testParam1;
    private String testParam2;
}
