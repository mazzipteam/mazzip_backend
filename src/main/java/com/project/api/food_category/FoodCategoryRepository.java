package com.project.api.food_category;

import com.project.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    Optional<FoodCategory> findByFoodCategoryId(Long foodCategoryId);
    Optional<FoodCategory> findByName(String name);
    void deleteByFoodCategoryId(Long foodCategoryId);
}

