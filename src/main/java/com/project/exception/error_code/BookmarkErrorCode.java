package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface BookmarkErrorCode {
    ErrorMessage BOOKMARK_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 북마크입니다.");
    ErrorMessage BOOKMARK_NOT_FOUND_BY_USER = new ErrorMessage(404, "해당 유저는 북마크가 없습니다.");
    ErrorMessage BOOKMARK_NOT_FOUND_BY_RESTAURANT = new ErrorMessage(404, "해당 레스토랑은 북마크가 없습니다.");
}
