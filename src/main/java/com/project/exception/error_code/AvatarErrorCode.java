package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface AvatarErrorCode {
    ErrorMessage AVATAR_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 아바타입니다.");
}
