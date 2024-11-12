package com.project.api.clothes;

import com.project.exception.ErrorMessage;

public interface ClothesErrorCode {
    ErrorMessage CLOTHES_NAME_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 의상명입니다.");
    ErrorMessage CLOTHES_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 의상입니다.");
    ErrorMessage CATEGORY_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 카테고리입니다.");
    ErrorMessage COST_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 가격입니다.");
    ErrorMessage IMAGE_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 이미지입니다.");
}
