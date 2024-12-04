package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "foreGround", columnDefinition="LONGBLOB")
    private byte[] foreGround;

    @Column(name = "interior", columnDefinition="LONGBLOB")
    private byte[] interior;

    @Column(name = "menu", columnDefinition="LONGBLOB")
    private byte[] menu;
}
