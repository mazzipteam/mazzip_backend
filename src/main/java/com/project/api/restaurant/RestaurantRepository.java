package com.project.api.restaurant;

import com.project.entity.restraunt.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByRestaurantId(Long restaurantId);
    Optional<Restaurant> findByName(String name);
    void deleteByRestaurantId(Long restaurantId);
}
