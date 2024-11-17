package com.project.api.menu;

import com.project.entity.Menu;
import com.project.entity.restraunt.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByMenuId(Long menuId);

    void deleteByMenuId(Long menuId);

    Optional<List<Menu>> findByRestaurant(Restaurant restaurant);
}
