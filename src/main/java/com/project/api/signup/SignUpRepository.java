package com.project.api.signup;

import com.project.entity.SignUp;
import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SignUpRepository extends JpaRepository<Restaurant, Long> {
    Optional<SignUp> findBySignUpId(Long signUpId);

    Optional<List<SignUp>> findByUser(User user);
}