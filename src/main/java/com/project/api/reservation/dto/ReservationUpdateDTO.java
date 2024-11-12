package com.project.api.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ReservationUpdateDTO {
    private Long reservationId;
    private String time;
    private String people;
}
