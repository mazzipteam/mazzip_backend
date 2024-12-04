package com.project.api.reservation;

import com.project.api.reservation.dto.ReservationCreateDTO;
import com.project.api.reservation.dto.ReservationUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    // TODO: 예약 상태 변경하는 API 필요
    
    // 예약 생성
    @PostMapping
    public ResponseEntity create(@RequestBody ReservationCreateDTO reservationCreateDTO) {
        var reservation = reservationService.create(reservationCreateDTO);
        var response = CommonResponse.builder().code(200).message("예약 생성 성공").data(reservation).build();
        return ResponseEntity.ok(response);
    }

    // 예약 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody ReservationUpdateDTO reservationUpdateDTO) {
        var reservation = reservationService.update(reservationUpdateDTO);
        var response = CommonResponse.builder().code(200).message("예약 수정 성공").data(reservation).build();
        return ResponseEntity.ok(response);
    }

    // 예약 삭제
    @DeleteMapping("/{reservationId}")
    public ResponseEntity delete(@PathVariable Long reservationId) {
        var reservation = reservationService.delete(reservationId);
        var response = CommonResponse.builder().code(200).message("예약 삭제 성공").data(reservation).build();
        return ResponseEntity.ok(response);
    }

    // 예약 조회
    @GetMapping("/{reservationId}")
    public ResponseEntity get(@PathVariable Long reservationId) {
        var reservation = reservationService.getReservation(reservationId);
        var response = CommonResponse.builder().code(200).message("예약 조회 성공").data(reservation).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity getAllByUser(@PathVariable Long userId) {
        var reservations = reservationService.getAllReservationByUser(userId);
        var response = CommonResponse.builder().code(200).message("예약 조회 성공").data(reservations).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/restaurant/{restaurantId}")
    public ResponseEntity getAllByRestaurant(@PathVariable Long restaurantId) {
        var reservations = reservationService.getAllReservationByRestaurant(restaurantId);
        var response = CommonResponse.builder().code(200).message("예약 조회 성공").data(reservations).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/accept/{reservationId}")
    public ResponseEntity accept(@PathVariable Long reservationId) {
        var reservation = reservationService.accept(reservationId);
        
        var response = CommonResponse.builder().code(200).message("예약 상태 변경 성공").data(reservation).build();
        return ResponseEntity.ok(response);
    }
}
