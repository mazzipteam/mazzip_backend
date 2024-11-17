package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface NoticeErrorCode {
    ErrorMessage NOTICE_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 알림입니다.");
    ErrorMessage NOTICE_NOT_FOUND_BY_USER = new ErrorMessage(404, "해당 유저는 알림이 없습니다.");
}
