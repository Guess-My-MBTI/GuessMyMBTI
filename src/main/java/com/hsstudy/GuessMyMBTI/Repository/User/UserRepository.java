package com.hsstudy.GuessMyMBTI.Repository.User;

import com.hsstudy.GuessMyMBTI.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
