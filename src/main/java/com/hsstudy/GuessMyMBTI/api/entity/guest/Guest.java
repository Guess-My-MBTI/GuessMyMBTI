package com.hsstudy.GuessMyMBTI.api.entity.guest;

import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

//import javax.persistence.Entity;
// DB테이블과 매핑

// 예시
// @Column(name = "GUEST_ID")
// @Id @GeneratedValue
// private Long id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Guest")
public class Guest {

    @Id @GeneratedValue
    @Column(name = "GUEST_ID")
    private Long id;

    @Column(name = "GUEST_NICKNAME")
    @Size(max = 45)
    private String nickname;

    @Column(name = "GUEST_AUTHORITY")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "GUEST_ANS")
    @Size(max = 45)
    private String answer;

    @Column(name = "RESULT_MBTI")
    @Size(max = 45)
    private String result;

}
