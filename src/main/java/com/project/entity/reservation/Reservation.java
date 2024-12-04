package com.project.entity.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private Integer people;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
