package com.hsstudy.GuessMyMBTI.api.domain.account.dto;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import lombok.Data;

@Data
public class LoginResponseDto {

    public boolean loginSuccess;
    public Account account;
//    public String kakaoAccessToken;
}