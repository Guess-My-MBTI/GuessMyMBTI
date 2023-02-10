package com.hsstudy.GuessMyMBTI.Repository;

import com.hsstudy.GuessMyMBTI.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Guest의 데이터 REPO
// JPA 이용

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByOwnerId(String id);
}
