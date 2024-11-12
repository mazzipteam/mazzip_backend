package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface MenuErrorCode {
    ErrorMessage MENU_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 메뉴입니다.");
    ErrorMessage RESTAURANT_ALREADY_EXISTS = new ErrorMessage(404, "이미 메뉴가 존재하는 레스토랑입니다.");
    ErrorMessage FOOD_CATEGORY_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 음식 카테고리입니다.");
    ErrorMessage PRICE_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 가격입니다.");
    ErrorMessage CHEAP_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 가성비입니다.");
    ErrorMessage IMAGE_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 이미지입니다.");
    ErrorMessage MAIN_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 메뉴입니다.");
    ErrorMessage IS_RESERVE_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 배달 가능 여부입니다.");
}
