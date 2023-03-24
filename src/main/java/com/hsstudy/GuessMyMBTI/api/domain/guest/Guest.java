package com.hsstudy.GuessMyMBTI.api.domain.guest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

//import javax.persistence.Entity;
// DB테이블과 매핑

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
    private String accuracy;

    @Column(name = "GUEST_COMMENT")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account owner;

}
