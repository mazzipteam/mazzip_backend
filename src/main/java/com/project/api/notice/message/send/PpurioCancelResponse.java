package com.project.api.notice.message.send;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PpurioCancelResponse {
    private String code;
    private String description;
}
