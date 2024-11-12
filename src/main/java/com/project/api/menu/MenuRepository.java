package com.project.api.menu;

import com.project.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Optional<Menu> findByMenuId(Long menuId);

    void deleteByMenuId(Long menuId);
}
