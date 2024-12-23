package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface UserErrorCode {
    ErrorMessage EMAIL_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 이메일입니다.");
    ErrorMessage USER_NOT_FOUND_BY_EMAIL = new ErrorMessage(404, "해당 이메일의 유저가 없습니다.");
    ErrorMessage USER_NOT_FOUND_BY_NICK_NAME = new ErrorMessage(404, "해당 별명의 유저가 없습니다.");
    ErrorMessage USER_NOT_FOUND_BY_PASSWORD = new ErrorMessage(404, "해당 비밀번호의 유저가 없습니다.");
    ErrorMessage NICKNAME_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 닉네임입니다.");
    ErrorMessage EMAIL_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 이메일입니다.");
    ErrorMessage TELNUM_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 전화번호입니다.");
    ErrorMessage ROLE_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 Role입니다.");
    ErrorMessage USER_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 유저입니다.");
}