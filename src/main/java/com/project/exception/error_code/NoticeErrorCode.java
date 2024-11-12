package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface NoticeErrorCode {
    ErrorMessage NOTICE_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 알림입니다.");
}
