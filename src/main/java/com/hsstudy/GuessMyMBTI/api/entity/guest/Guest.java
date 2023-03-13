package com.hsstudy.GuessMyMBTI.api.entity.guest;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    @Id
    @GeneratedValue
    @Column(name = "GUEST_ID")
    private Long id;

    @Column(name = "GUEST_NICKNAME")
    @Size(max = 45)
    private String nickname;

    @Column(name = "GUEST_AUTHORITY")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "GUEST_ANS") // 결과 문자열
    @Size(max = 45)
    private String answer;

    @Column(name = "RESULT_MBTI") // 게스트가 생각한 owner의 mbti
    @Size(max = 45)
    private String result;

    @Column(name = "GUEST_ACC") // 결과 % 정확도
    private int accuracy;

    @Column(name = "GUEST_COMMENT")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account ownerId;

}
