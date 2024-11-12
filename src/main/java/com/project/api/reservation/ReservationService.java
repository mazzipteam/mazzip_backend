package com.project.api.reservation;

import com.project.api.reservation.dto.ReservationCreateDTO;
import com.project.api.reservation.dto.ReservationUpdateDTO;
import com.project.entity.reservation.Reservation;
import com.project.api.restaurant.RestaurantService;
import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.project.exception.error_code.ReservationErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public Reservation create(ReservationCreateDTO reservationCreateDTO) {
        var restaurant = restaurantService.getRestaurant(reservationCreateDTO.getRestaurantId());
        var user = userService.getUser(reservationCreateDTO.getUserId());

        // 1. [예외처리] 현재 시간보다 이전 시간을 요청하는 경우
        // TODO: 식당 운영시간도 예외처리 할 것(현재는 식당 운영시간 정보 없음)
        if(reservationCreateDTO.getTime().isBefore(LocalDateTime.now()))
            throw new ControlledException(INCORRECT_TIME);

        // 2. [예외처리] 예약할 수 없는 인원 수인 경우
        if(reservationCreateDTO.getPeople() < 1)
            throw new ControlledException(INCORRECT_NUMBER_PEOPLE);

        var reservation = Reservation.builder()
                .time(reservationCreateDTO.getTime())
                .people(reservationCreateDTO.getPeople())
                .customer(user)
                .restaurant(restaurant)
                .build();

        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation update(ReservationUpdateDTO reservationUpdateDTO) {
        var reservation = getReservation(reservationUpdateDTO.getReservationId());

        // TODO: 식당 운영시간도 예외처리 할 것(현재는 식당 운영시간 정보 없음)
        if(!reservationUpdateDTO.getTime().isEmpty()) {
            LocalDateTime time;
            // 1. [예외처리] time이 LocalDateTime의 형식이 아닌 경우
            try {
                time = LocalDateTime.parse(reservationUpdateDTO.getTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                throw new ControlledException(INCORRECT_TIME);
            }
            // 2. [예외처리] 현재 시간보다 이전 시간을 요청하는 경우
            if (time.isBefore(LocalDateTime.now()))
                throw new ControlledException(INCORRECT_TIME);

            reservation.setTime(time);
        }

        if(!reservationUpdateDTO.getPeople().isEmpty()) {
            // 3. [예외처리] people이 Integer의 형식이 아닌 경우
            try {
                Integer people = Integer.parseInt(reservationUpdateDTO.getPeople());
                // 4. [예외처리] 예약할 수 없는 인원 수인 경우
                if(people < 1)
                    throw new ControlledException(INCORRECT_NUMBER_PEOPLE);

                reservation.setPeople(people);
            } catch (NumberFormatException e) {
                throw new ControlledException(INCORRECT_NUMBER_PEOPLE);
            }
        }

        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation delete(Long reservationId) {
        var reservation = getReservation(reservationId);

        reservationRepository.deleteByReservationId(reservationId);
        return reservation;
    }

    public Reservation getReservation(Long reservationId) {
        var reservation = reservationRepository.findByReservationId(reservationId)
                .orElseThrow(()->new ControlledException(RESERVATION_NOT_FOUND));

        return reservation;
    }
}
