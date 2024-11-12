package com.project.api.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ReservationCreateDTO {
    private LocalDateTime time;
    private Integer people;
    private Long userId;
    private Long restaurantId;
}
