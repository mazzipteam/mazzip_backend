package com.project.api.restaurant;

import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByRestaurantId(Long restaurantId);
    Optional<Restaurant> findByName(String name);
    void deleteByRestaurantId(Long restaurantId);

    Optional<Restaurant> findByUser(User user);
}
