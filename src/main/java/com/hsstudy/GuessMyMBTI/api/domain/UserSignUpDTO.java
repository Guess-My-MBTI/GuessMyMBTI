package com.hsstudy.GuessMyMBTI.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDTO {

    private String email;
    private String password;
    private String nickname;
    private int age;
    private String city;
}