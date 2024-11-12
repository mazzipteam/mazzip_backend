package com.project.entity;

import com.project.entity.restraunt.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "restaurant_image")
public class RestaurantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private byte[] foreGround;

    @Column(nullable = false)
    private byte[] interior;

    @Column(nullable = false)
    private byte[] menu;
}
