package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface TokenErrorCode {
    ErrorMessage TOKEN_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 토큰입니다.");
}
