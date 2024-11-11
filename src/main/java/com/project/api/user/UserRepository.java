package com.project.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickName(String nickName);

    Optional<User> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
