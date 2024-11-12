package com.project.api.avatar;

import com.project.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByAvatarId(Long avatarId);
    void deleteByAvatarId(Long avatarId);
}
