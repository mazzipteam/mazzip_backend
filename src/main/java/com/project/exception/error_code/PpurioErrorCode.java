package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface PpurioErrorCode {
    ErrorMessage CONTENT_IS_TOO_LONG = new ErrorMessage(404, "본문이 너무 깁니다. (본문 크기는 최대 90/2000Byte입니다.)");
    ErrorMessage FILE_IS_TOO_BIG = new ErrorMessage(404, "이미지가 너무 큽니다. (이미지 크기는 최대 300KB입니다.)");
    ErrorMessage MULTIPART_FILE_NOT_FOUND = new ErrorMessage(404, "잘못된 MultipartFile입니다.");
}
