package com.project.api.clothes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Optional<Clothes> findByClothesId(Long clothesId);
    Optional<Clothes> findByName(String clothesName);

    void deleteByClothesId(Long clothesId);
}
