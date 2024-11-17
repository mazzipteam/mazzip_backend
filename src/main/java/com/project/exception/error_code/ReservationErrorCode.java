package com.project.exception.error_code;

import com.project.exception.ErrorMessage;

public interface ReservationErrorCode {
    ErrorMessage RESERVATION_NOT_FOUND = new ErrorMessage(404, "존재하지 않는 예약입니다.");
    ErrorMessage RESERVATION_NOT_FOUND_BY_USER = new ErrorMessage(404, "해당 유저가 한 예약이 없습니다.");
    ErrorMessage RESERVATION_NOT_FOUND_BY_RESTAURANT = new ErrorMessage(404, "해당 레스토랑에 대한 예약이 없습니다.");
    ErrorMessage INCORRECT_NUMBER_PEOPLE = new ErrorMessage(404, "잘못된 인원수입니다.");
    ErrorMessage INCORRECT_TIME = new ErrorMessage(404, "예약할 수 없는 시간입니다.");
    ErrorMessage PEOPLE_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 인원입니다.");
    ErrorMessage TIME_INCORRECT_FORMAT = new ErrorMessage(404, "잘못된 형식의 예약시간입니다.");
}
