package com.project.api.report;

import com.project.exception.ErrorMessage;

public interface ReportErrorMessage {
    ErrorMessage REPORT_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 신고입니다.");
    ErrorMessage CATEGORY_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 카테고리입니다.");
}
