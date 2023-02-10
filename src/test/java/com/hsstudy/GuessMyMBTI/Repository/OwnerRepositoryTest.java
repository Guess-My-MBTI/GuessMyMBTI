package com.hsstudy.GuessMyMBTI.Repository;

import com.hsstudy.GuessMyMBTI.Entity.Owner;
import com.hsstudy.GuessMyMBTI.Oauth.Entity.RoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnerRepositoryTest {

    @Autowired
    OwnerRepository ownerRepository;

    @Test
    void findByOwnerId() { // id, role type, nickname
//        Long id, RoleType roleType, String nickname, String ans, String mbti
        ownerRepository.save(new Owner(1L, RoleType.OWNER, "양종욱", null, null));
        ownerRepository.save(new Owner(2L, RoleType.OWNER, "JU", null, null));

        Owner owner = ownerRepository.findByOwnerId("양종욱");
        System.out.println("이름 : "+owner.getId() +
                " RoleType : " + owner.getRoleType() +
                " nickname : " + owner.getNickname() +
                " Ans : " + owner.getAns() +
                " MBTI : " + owner.getMbti());

    }
}