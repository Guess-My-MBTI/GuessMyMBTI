package com.hsstudy.GuessMyMBTI.api.domain.account;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "KAKAOTOKEN")
@Entity
public class KakaoToken {

    @Id
    @Column(name="KAKAO_USER_ID")
    private Long id;

    @Column(name="KAKAO_ACCESS_TOKEN")
    private String kakaoAccessToken;

}
