package com.hsstudy.GuessMyMBTI.api.repository;

import com.hsstudy.GuessMyMBTI.api.domain.account.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /* 토큰 존재 유무 확인 */
    Optional<RefreshToken> findByKey(Long key);
    Optional<RefreshToken> findByToken(String token);
}