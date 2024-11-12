package com.project.api.bookmark;

import com.project.exception.ErrorMessage;

public interface BookmarkErrorCode {
    ErrorMessage BOOKMARK_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 북마크입니다.");
}
