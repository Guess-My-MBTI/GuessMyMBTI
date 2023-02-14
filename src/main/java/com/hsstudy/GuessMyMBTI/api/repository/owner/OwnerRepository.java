package com.hsstudy.GuessMyMBTI.api.repository.owner;

import com.hsstudy.GuessMyMBTI.api.entity.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Guest의 데이터 REPO
// JPA 이용

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findById(String id);

//    Owner findByNickname(String nickname);
}
