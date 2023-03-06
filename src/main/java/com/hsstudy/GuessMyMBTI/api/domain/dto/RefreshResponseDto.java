package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import lombok.Data;

@Data
public class RefreshResponseDto {

    String accessToken;
    Account account;
}