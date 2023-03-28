package com.hsstudy.GuessMyMBTI.api.repository;

import com.hsstudy.GuessMyMBTI.api.domain.guest.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Guest의 데이터 REPO
// JPA 이용

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findById(String id);
    Optional<Guest> findByNicknameAndId(String nickname, Long id);
    Optional<Guest> findByNickname(String nickname);

//    @Query(value = "SELECT * FROM GUEST WHERE NICKNAME = ?1 AND RESULT_MBTI IS NULL", nativeQuery = true)
    Optional<Guest> findByNicknameAndResultIsNull(String nickname);

    List<Guest> findAllByNickname(String nickname);


}
