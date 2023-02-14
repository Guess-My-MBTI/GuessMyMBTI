package com.hsstudy.GuessMyMBTI.api.testJwt.config;

import com.hsstudy.GuessMyMBTI.api.testJwt.jwt.JwtFilter;
import com.hsstudy.GuessMyMBTI.api.testJwt.jwt.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// SecurityConfigureAdapter 를 상속받고 TokenProvider 를 주입받아서
// JwtFilter 를 통해 Security 로직에 필터 등록
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;
    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        // 시큐리티 로직에 아까 만든 jwtFilter 를 등록
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
// 그 다음
// 유효한 자격증명을 제공하지 않고 접근하려 할 때
// 401 Unauthorized 에러를 리턴할
// JwtAuthenticationEntryPoint 클래스 생성
