package com.project.api.memo;

import com.project.exception.ErrorMessage;

public interface MemoErrorCode {
    ErrorMessage MEMO_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 메모입니다.");
}
