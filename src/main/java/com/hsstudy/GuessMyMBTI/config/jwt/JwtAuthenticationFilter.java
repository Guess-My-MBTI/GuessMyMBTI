package com.hsstudy.GuessMyMBTI.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    /**
    실제 필터링 로직이 수행될 전체 메서드 doFilterInternal
    본 메서드에서는 JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할을 수행
    authentication > SecurityContext > ContextHolder 인거 알지..? ~
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = resolveToken(request);

        // 2. validateToken 메서드로 토큰의 유효성을 검사
        // 정상 토큰이면 해당 토큰이 authentication 을 뱉으므로 이걸 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰을 꺼내오는 메서드 resolveToken
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
//
//    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
//        this.jwtProvider = jwtProvider;
//    }
//
//    // request 로 들어오는 Jwt 의 유효성을 검증 - JwtProvider.validationToken() 을 필터로서 FilterChain 에 추가
//    @Override
//    public void doFilter(ServletRequest request,
//                         ServletResponse response,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        // request 에서 token 을 취한다.
//        String token = jwtProvider.resolveToken((HttpServletRequest) request);
//
//        // 검증
//        log.info("[Verifying token]");
//        log.info(((HttpServletRequest) request).getRequestURL().toString());
//
//        if (token != null && jwtProvider.validationToken(token)) {
//            Authentication authentication = jwtProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        log.info("[request 토큰 검증이 완료되었습니다.]");
//        filterChain.doFilter(request, response);
//    }
}