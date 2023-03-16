package com.hsstudy.GuessMyMBTI.api.domain.account;


import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Entity 어노테이션 붙이면 알아서 JPA 연동됨
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
@Entity
public class Account extends BaseTimeEntity { // 예약어가 이미 존재하므로 users로 바꾸어 지정해야함

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db의 id 값이 자동으로 생성되도록 한 경우 꼭 붙여줘야 하는 어노테이션
    private Long id;

    @Column
    private String loginType;

    @Column
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    private String kakaoName; //카카오닉네임

    @Column
    private String nickname; //사용자별명

    @Column(nullable = false)
    private String email;

    /* 회원가입 과정에서는 프로필 사진을 나중에 등록할 수 있게 nullable */
    @Column
    private String picture;

    // 오너의 mbti -> INFJ
    @Column
    private String mbti;

    // 오너의 문자열 -> EEEEEIIIIIJJJJJPPPPP
    @Column
    private String result;

    // Guest의 아이디
    @OneToMany
    @JoinColumn(name = "ownerId")
    private List<Guest> guests = new ArrayList<>();

}