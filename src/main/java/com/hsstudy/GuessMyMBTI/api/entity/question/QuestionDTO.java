package com.hsstudy.GuessMyMBTI.api.entity.question;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDTO {

    private Long id;

    private String type;

    private String content;

    private String answer1;

    private String answer2;

}
