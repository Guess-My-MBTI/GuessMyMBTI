package com.hsstudy.GuessMyMBTI.api.entity.mbti;


import javax.persistence.*;

@Entity
@Table(name = "MBTI_EXPLAIN")
public class MbtiExplain {

    @Id @GeneratedValue
    @Enumerated(EnumType.STRING)
    @Column(name = "EXPLAIN_MBTI")
    private Mbti mbti;

    @Column(name = "EXPLAIN_MBTI_CT")
    private String content;

    @Column(name = "EXPLAIN_MBTI_NM")
    private String name;


}
