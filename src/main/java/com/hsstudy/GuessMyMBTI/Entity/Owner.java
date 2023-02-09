package com.hsstudy.GuessMyMBTI.Entity;

import lombok.*;

import javax.persistence.*;

// DB테이블과 매핑

@Entity
@Getter
@Setter
@NoArgsConstructor // no기본 생성자
@AllArgsConstructor // args 객체가 가지고 있는 변수가 가지고 있는 생성자를 민들어줌
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    private Long id;

    @Column(name = "OWNER_NICKNAME")
    private String nickname; // 카톡 이름, 구글 이름




}
