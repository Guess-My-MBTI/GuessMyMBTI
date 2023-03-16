package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import lombok.Data;

@Data
public class RefreshResponseDto {

    String accessToken;
    Account account;
}