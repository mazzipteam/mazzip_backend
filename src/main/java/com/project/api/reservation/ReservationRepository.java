package com.project.api.reservation;

import com.project.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findByReservationId(Long reservationId);

    void deleteByReservationId(Long reservationId);
}
