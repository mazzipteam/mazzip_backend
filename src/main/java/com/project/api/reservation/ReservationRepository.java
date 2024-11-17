package com.project.api.reservation;

import com.project.entity.reservation.Reservation;
import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationId(Long reservationId);

    void deleteByReservationId(Long reservationId);

    Optional<List<Reservation>> findByUser(User user);

    Optional<List<Reservation>> findByRestaurant(Restaurant restaurant);
}
