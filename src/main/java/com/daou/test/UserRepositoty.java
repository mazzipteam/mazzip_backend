package com.daou.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoty extends JpaRepository<User, Integer> {
    Optional<User> findByUserId(String userId);
}
