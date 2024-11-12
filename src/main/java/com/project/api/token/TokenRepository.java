package com.project.api.token;

import com.project.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByTokenId(Long tokenId);

    void deleteByTokenId(Long tokenId);
}
