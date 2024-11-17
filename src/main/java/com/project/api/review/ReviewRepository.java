package com.project.api.review;

import com.project.entity.Review;
import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(Long reviewId);

    void deleteByReviewId(Long reviewId);

    Optional<List<Review>> findByUser(User user);

    Optional<List<Review>> findByRestaurant(Restaurant restaurant);
}
