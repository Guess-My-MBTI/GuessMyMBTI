package com.hsstudy.GuessMyMBTI.api.entity.guest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import javax.persistence.Entity;
// DB테이블과 매핑

// 예시
// @Column(name = "GUEST_ID")
// @Id @GeneratedValue
// private Long id;

@Entity
@Getter
@Setter
@Table(name = "Guest")
public class Guest {

    @Id @GeneratedValue
    @Column(name = "GUEST_ID")
    private Long id;

    @Column(name = "GUEST_NICKNAME")
    private String nickname;

    @Column(name = "GUEST_ANS")
    private String answer;

    @Column(name = "RESULT_MBTI")
    private String result;

}
