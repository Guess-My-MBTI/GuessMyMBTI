//package com.hsstudy.GuessMyMBTI.api.entity.guest;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//
////import javax.persistence.Entity;
//// DB테이블과 매핑
//
//// 예시
//// @Column(name = "GUEST_ID")
//// @Id @GeneratedValue
//// private Long id;
//
//@Getter
//@Setter
//@NoArgsConstructor // no기본 생성자
//@AllArgsConstructor // @RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다. -> @Autowired 생략
//@Entity
//@Builder
//@Table(name = "GUEST")
//public class Guest {
//
//    @JsonIgnore
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "GUEST_ID")
//    private Long id; // DB에 저장될 개별 id값
//
//}
