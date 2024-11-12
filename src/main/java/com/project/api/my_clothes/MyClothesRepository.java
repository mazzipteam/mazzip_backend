package com.project.api.my_clothes;

import com.project.entity.MyClothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface MyClothesRepository  extends JpaRepository<MyClothes, Long> {
    Optional<MyClothes> findByMyClothesId(Long myClothesId);
    Optional<List<MyClothes>> findByAvatarId(Long avatarId);

    Optional<MyClothes> findByAvatarIdAndMyClothesId(Long avatarId, Long myClothesId);

    void deleteByMyClothesId(Long myClothesId);
}
