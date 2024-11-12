package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface RestaurantImageErrorMessage {
    ErrorMessage RESTAURANT_IMAGE_ALREADY_EXISTS = new ErrorMessage(404, "해당 식당에는 이미 맛집 이미지가 존재합니다.");
    ErrorMessage IMAGE_OF_INCORRECT_FORMAT = new ErrorMessage(404, "이미지가 바이너리 형태가 아닙니다.");
    ErrorMessage RESTAURANT_IMAGE_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 맛집 이미지입니다.");
}
