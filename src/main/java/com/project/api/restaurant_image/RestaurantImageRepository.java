package com.project.api.restaurant_image;

import com.project.entity.RestaurantImage;
import com.project.entity.restraunt.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
    Optional<RestaurantImage> findByRestaurantImageId(Long restaurantImageId);
    Optional<RestaurantImage> findByRestaurant(Restaurant restaurant);

    void deleteByRestaurantImageId(Long restaurantImageId);
}
