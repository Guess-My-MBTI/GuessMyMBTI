package com.hsstudy.GuessMyMBTI.api.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplainMbtiDTO {

    private String mbti;

    private String charType;

    private String name;
}
