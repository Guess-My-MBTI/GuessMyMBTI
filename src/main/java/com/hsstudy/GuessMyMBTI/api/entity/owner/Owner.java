package com.hsstudy.GuessMyMBTI.api.entity.owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsstudy.GuessMyMBTI.Oauth.Entity.ProviderType;
import com.hsstudy.GuessMyMBTI.Oauth.Entity.RoleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// DB테이블과 매핑

@Getter
@Setter
@NoArgsConstructor // no기본 생성자
@AllArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다. -> @Autowired 생략
@Entity
@Builder
@Table(name = "OWNER")
public class Owner {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    private Long id; // DB에 저장될 개별 id값

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @Size(max = 128)
    private String password;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "OWNER_NICKNAME")
    @NotNull
    private String nickname; // 카톡 이름, 구글 이름 가져옴

    @Column(name = "OWNER_ANS")
    @Size(max = 20)
    private String ans; // 오너가 작성한 결과값 20개

    @Column(name = "OWNER_MBTI")
    @Size(max = 4)
    private String mbti; // 오너가 작성한 ans를 토대로 만들어 줄 MBTI

    // 생성자는 @AllArgsConstructor 에서 만들어줌

}