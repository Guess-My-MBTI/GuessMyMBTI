package com.hsstudy.GuessMyMBTI.api.domain.explainMbti;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExplainMbtiDTO {
    private String mbti;
    private String charType;
    private String name;
}
