package com.hsstudy.GuessMyMBTI.api.domain.account.dto;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import lombok.Data;

@Data
public class SignupResponseDto {

    Account account;
    String result;
}