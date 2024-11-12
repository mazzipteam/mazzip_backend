package com.project.api.report.dto;

import com.project.api.report.report.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReportCreateDTO {
    private Long reviewId;
    private Long userId;
    private Category category;
    private String title;
    private String description;
}
