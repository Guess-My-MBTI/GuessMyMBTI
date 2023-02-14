package com.hsstudy.GuessMyMBTI.api.testJwt.dto;
// Token 정보를 Response 할 때 사용할 TokenDto 클래스

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
}
