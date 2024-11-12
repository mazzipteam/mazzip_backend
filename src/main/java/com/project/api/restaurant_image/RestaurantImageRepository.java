package com.project.api.restaurant_image;

import com.project.entity.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
    Optional<RestaurantImage> findByRestaurantImageId(Long restaurantImageId);
    Optional<RestaurantImage> findByRestaurantId(Long restaurantId);

    void deleteByRestaurantImageId(Long restaurantImageId);
}
