package com.hsstudy.GuessMyMBTI.api.domain.account.dto;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import lombok.Data;

@Data
public class SignupRequestDto {

    public String nickname;
    public String picture;
    public Account account;
}