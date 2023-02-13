package com.hsstudy.GuessMyMBTI.api.entity.owner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// DB테이블과 매핑

@Getter
@Setter
@NoArgsConstructor // no기본 생성자
//@AllArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다. -> @Autowired 생략
@Entity
@Builder
@Table(name = "Owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    private Long id; // DB에 저장될 개별 id값

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @Size(max = 128)
    private String password;

    @Column(name = "OWNER_NICKNAME")
    @NotNull
    private String ownerNickname; // 카톡 이름, 구글 이름 가져옴

    @Column(name = "OWNER_EMAIL")
    private String ownerEmail;

    @Column(name = "OWNER_ANS")
    @Size(max = 20)
    private String answer; // 오너가 작성한 결과값 20개

    @Column(name = "OWNER_MBTI")
    @Size(max = 4)
    private String ownerMbti; // 오너가 작성한 ans를 토대로 만들어 줄 MBTI


//    @Column(name = "PROVIDER_TYPE", length = 20)
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private ProviderType providerType;
//
//    @Column(name = "ROLE_TYPE", length = 20)
//    @Enumerated(EnumType.STRING)
//    @NotNull
//    private RoleType roleType;

    @Builder
    public Owner(Long ownerId, Long ownerCode, String ownerNickname, String ownerEmail, String ownerAns, String ownerMbti) {
        this.ownerId = ownerId;
        this.ownerCode = ownerCode;
        this.ownerNickname = ownerNickname;
        this.ownerEmail = ownerEmail;
        this.ownerAns = ownerAns;
        this.ownerMbti = ownerMbti;
    }
}
