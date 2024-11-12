package com.project.exception.errorcode;

import com.project.exception.ErrorMessage;

public interface FoodCategoryErrorCode {
    ErrorMessage FOOD_CATEGORY_NAME_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 음식 카테고리명입니다.");
    ErrorMessage FOOD_CATEGORY_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 음식 카테고리입니다.");
}
