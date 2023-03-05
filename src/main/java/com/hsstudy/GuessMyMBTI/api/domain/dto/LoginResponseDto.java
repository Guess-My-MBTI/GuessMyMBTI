package com.hsstudy.GuessMyMBTI.api.domain.dto;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import lombok.Data;

@Data
public class LoginResponseDto {

    public boolean loginSuccess;
    public Account account;
    public String kakaoAccessToken;
}