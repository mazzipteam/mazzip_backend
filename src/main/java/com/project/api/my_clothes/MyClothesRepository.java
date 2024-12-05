package com.project.api.my_clothes;

import com.project.entity.Avatar;
import com.project.entity.MyClothes;
import com.project.entity.clothes.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MyClothesRepository  extends JpaRepository<MyClothes, Long> {
    Optional<MyClothes> findByMyClothesId(Long myClothesId);
    Optional<List<MyClothes>> findByAvatar(Avatar avatar);

    Optional<MyClothes> findByAvatarAndMyClothesId(Avatar avatar, Long myClothesId);

    void deleteByMyClothesId(Long myClothesId);

    Optional<MyClothes> findByClothesAndAvatar(Clothes clothes, Avatar avatar);
}
