package com.hsstudy.GuessMyMBTI.api.repository;

import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Guest의 데이터 REPO
// JPA 이용

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findById(String id);
    Optional<Guest> findByNicknameAndId(String nickname, Long id);
}
