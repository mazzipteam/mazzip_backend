package com.project.api.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReportUpdateDTO {
    private Long reportId;
    private String category;
    private String title;
    private String description;
}
