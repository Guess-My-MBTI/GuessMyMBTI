package com.hsstudy.GuessMyMBTI.api.testJwt.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 유효한 자격증명을 제공하지 않고 접근하려 할 때
// 401 Unauthorized 에러를 리턴할
// JwtAuthenticationEntryPoint 클래스
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException authenticationException) throws IOException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

// 그 다음
// 필요한 권한이 존재하지 않는 경우에
// 403 Forbidden 에러를 리턴하기 위해
// JwtAccessDeniedHandler 를 생성
