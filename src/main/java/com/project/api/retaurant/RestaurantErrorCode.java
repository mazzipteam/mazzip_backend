package com.project.api.retaurant;

import com.project.exception.ErrorMessage;

public interface RestaurantErrorCode {
    ErrorMessage CATEGORY_OF_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 Category입니다.");
    ErrorMessage RESTAURANT_NAME_ALREADY_EXISTS = new ErrorMessage(404, "이미 존재하는 레스토랑명입니다.");
    ErrorMessage RESTAURANT_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 맛집입니다.");
}
