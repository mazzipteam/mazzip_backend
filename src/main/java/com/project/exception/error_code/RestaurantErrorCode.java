package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface RestaurantErrorCode {
    ErrorMessage CATEGORY_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 Category입니다.");
    ErrorMessage TAKE_OUT_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 배달가능여부입니다.");
    ErrorMessage RESTAURANT_NAME_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 레스토랑명입니다.");
    ErrorMessage RESTAURANT_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 맛집입니다.");
    ErrorMessage RESTAURANT_NOT_FOUND_BY_USER = new ErrorMessage(404, "해당 유저에게 존재하지 않는 맛집입니다.");
}
