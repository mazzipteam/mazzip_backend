package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface BookmarkErrorCode {
    ErrorMessage BOOKMARK_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 북마크입니다.");
}
