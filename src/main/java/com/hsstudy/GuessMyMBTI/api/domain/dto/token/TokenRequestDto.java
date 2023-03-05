package com.hsstudy.GuessMyMBTI.api.domain.dto.token;

import lombok.Builder;
import lombok.Data;

@Data
public class TokenRequestDto {

    String accessToken;
    String refreshToken;

    @Builder
    public TokenRequestDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}