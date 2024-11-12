package com.project.api.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TestCreateDTO {
    private String testParam1;
    private String testParam2;
    private String testParam3;
}