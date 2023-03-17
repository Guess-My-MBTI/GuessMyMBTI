package com.hsstudy.GuessMyMBTI.api.domain.questions;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "Question")
public class Question {

    @Id @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column(name = "QUESTION_TYPE")
    private String type;

    @Column(name = "QUESTION_CT")
    private String content;

    @Column(name = "QUESTION_A1")
    private String answer1;

    @Column(name = "QUESTION_A2")
    private String answer2;


}
