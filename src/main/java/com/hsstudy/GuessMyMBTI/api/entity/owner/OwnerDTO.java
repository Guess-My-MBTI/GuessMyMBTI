package com.hsstudy.GuessMyMBTI.api.entity.owner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OwnerDTO {
    private Long id; // DB에 저장될 개별 id값
    private String password;
    private String ownerNickname; // 카톡 이름, 구글 이름 가져옴
    private String ownerEmail;
    private String answer; // 오너가 작성한 결과값 20개
    private String ownerMbti; // 오너가 작성한 ans를 토대로 만들어 줄 MBTI
    private String token;

}
