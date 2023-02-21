package com.hsstudy.GuessMyMBTI.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ExplainMbti")
public class ExplainMbti {
    @Id
    @Column(name = "EXPLAINMBTI_MBTI")
    private String mbti;

    @Column(name = "EXPLAINMBTI_NM")
    private String name;

    @Column(name = "EXPLAINMBTI_CT")
    private String charType;

}
