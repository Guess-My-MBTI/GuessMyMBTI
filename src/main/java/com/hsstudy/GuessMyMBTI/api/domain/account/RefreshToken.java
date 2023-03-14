package com.hsstudy.GuessMyMBTI.api.domain.account;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity
public class RefreshToken {

    // DB 인덱스용으로 저장
    // refresh token 으로 유저를 특정할 수 없도록 PK 지정
    // GeneratedValue 에러였음 씌앙 ㅠ
    @Id
    @Column(name = "token_key", nullable = false)
    private Long key;

    @Column(name = "token_value", nullable = false)
    private String token;

    @Builder
    public RefreshToken(Long key, String token) {
        this.key = key;
        this.token = token;
    }

    public RefreshToken updateToken(String token) {
        this.token = token;
        return this;
    }
}