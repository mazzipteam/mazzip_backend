package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface MemoErrorCode {
    ErrorMessage MEMO_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 메모입니다.");
    ErrorMessage MEMO_NOT_FOUND_BY_USER = new ErrorMessage(404, "해당 유저는 메모가 없습니다.");
}
