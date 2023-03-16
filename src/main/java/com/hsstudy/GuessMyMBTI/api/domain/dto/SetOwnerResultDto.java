package com.hsstudy.GuessMyMBTI.api.domain.dto;

import lombok.Data;

@Data
public class SetOwnerResultDto {
    private Long id;
    String mbti;
    String result;
}
