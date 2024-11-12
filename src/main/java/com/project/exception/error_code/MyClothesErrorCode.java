package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface MyClothesErrorCode {
    ErrorMessage MY_CLOTHES_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 내 의상입니다.");
    ErrorMessage MY_CLOTHES_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 내 의상입니다.");
    ErrorMessage MY_CLOTHES_NOT_FOUND_IN_AVATAR = new ErrorMessage(404, "내 의상 조회가 불가능한 아바타입니다.");
    ErrorMessage WEAR_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 착용여부입니다.");
}
