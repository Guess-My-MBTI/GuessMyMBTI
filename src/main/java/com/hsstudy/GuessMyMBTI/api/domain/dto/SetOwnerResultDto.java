package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import lombok.Data;

@Data
public class SetOwnerResultDto {
    private Long id;
    String mbti;
    String result;
}
