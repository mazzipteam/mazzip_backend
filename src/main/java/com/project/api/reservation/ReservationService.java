package com.project.api.reservation;

import com.project.api.avatar.AvatarService;
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
import java.util.List;

import static com.project.entity.reservation.State.DONE;
import static com.project.entity.reservation.State.NOT_YET;
import static com.project.exception.error_code.ReservationErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final AvatarService avatarService;

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
                .user(user)
                .restaurant(restaurant)
                .state(NOT_YET)
                .build();

        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation update(ReservationUpdateDTO reservationUpdateDTO) {
        var reservation = getReservation(reservationUpdateDTO.getReservationId());

        // TODO: 식당 운영시간도 예외처리 할 것(현재는 식당 운영시간 정보 없음)
        if(reservationUpdateDTO.getTime()!= null) {
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

        if(reservationUpdateDTO.getPeople()!= null) {
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

        reservationRepository.deleteById(reservationId);
        return reservation;
    }

    public Reservation getReservation(Long reservationId) {
        var reservation = reservationRepository.findByReservationId(reservationId)
                .orElseThrow(()->new ControlledException(RESERVATION_NOT_FOUND));

        return reservation;
    }

    public List<Reservation> getAllReservationByUser(Long userId) {
        var user = userService.getUser(userId);
        var reservations = reservationRepository.findByUser(user)
                .orElseThrow(()->new ControlledException(RESERVATION_NOT_FOUND_BY_USER));

        return reservations;
    }

    public List<Reservation> getAllReservationByRestaurant(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var reservations = reservationRepository.findByRestaurant(restaurant)
                .orElseThrow(()->new ControlledException(RESERVATION_NOT_FOUND_BY_RESTAURANT));

        return reservations;
    }

    /**
     * 1. 예약승인 버튼을 누르면 해당 예약의 state를 DONE으로 바꾼다.
     *
     * 2. 해당 예약을 했던 유저의 아바타 레벨을 올린다.
     *
     * 3. 그 아바타의 레벨에 맞는 내의상이 최신화된다.
     *
     */
    public Reservation accept(Long reservationId) {
        var reservation = getReservation(reservationId);
        reservation.setState(DONE);
        reservationRepository.save(reservation);

        var user = reservation.getUser();
        var avatar = avatarService.getAvatarByUserId(user.getUserId());
        avatarService.levelUp(avatar.getAvatarId());

        return reservation;
    }
}
