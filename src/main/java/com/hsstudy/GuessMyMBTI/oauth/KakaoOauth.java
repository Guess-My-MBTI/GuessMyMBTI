package com.hsstudy.GuessMyMBTI.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOauth implements SocialOauth {

    @Override
    public String getOauthRedirectURL() {
        return null;
    }

    @Override
    public String requestAccessToken(String code) {
        return null;
    }

    @Override
    public SocialLoginType type() {
        return SocialOauth.super.type();
    }
}
