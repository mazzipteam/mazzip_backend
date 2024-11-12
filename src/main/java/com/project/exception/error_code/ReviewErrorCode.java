package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface ReviewErrorCode {
    ErrorMessage REVIEW_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 리뷰입니다.");
    ErrorMessage IMAGE_OF_INCORRECT_FORMAT = new ErrorMessage(404, "이미지가 바이너리 형태가 아닙니다.");
}
