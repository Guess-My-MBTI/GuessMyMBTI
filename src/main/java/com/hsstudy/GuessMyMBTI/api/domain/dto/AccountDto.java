package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AccountDto {

    private Long id;

    private String loginType;

    private Authority authority;

    private String kakaoName; //카카오닉네임

    private String nickname; //사용자별명

    private String email;

    /* 회원가입 과정에서는 프로필 사진을 나중에 등록할 수 있게 nullable */
    private String picture;

    // 오너의 mbti -> INFJ
    private String mbti;

    // 오너의 문자열 -> EEEEEIIIIIJJJJJPPPPP
    private String result;

    // Guest의 아이디
    private List<Guest> guests = new ArrayList<>();
}
