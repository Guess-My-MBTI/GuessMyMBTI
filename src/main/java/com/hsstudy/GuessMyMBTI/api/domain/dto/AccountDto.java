package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountDto {

    public Account account;
    public Long id;

    public String loginType;

    public Authority authority;

    public String kakaoName; //카카오닉네임

    public String nickname; //사용자별명

    public String email;

    /* 회원가입 과정에서는 프로필 사진을 나중에 등록할 수 있게 nullable */
    public String picture;

    // 오너의 mbti -> INFJ
    public String mbti;

    // 오너의 문자열 -> EEEEEIIIIIJJJJJPPPPP
    public String result;

    // Guest의 아이디
    public List<Guest> guests = new ArrayList<>();

}
