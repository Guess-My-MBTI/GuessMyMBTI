package com.hsstudy.GuessMyMBTI.Oauth.Token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Getter
public class AuthToken {
    private final String token;
    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

    // 토큰에 데이터 전달
    AuthToken(String id, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    AuthToken(String id, String role, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, role, expiry);
    }

    // Method Overriding 방식
    // 만료일(expiry) 토큰 생성
    private String createAuthToken(String id, Date expiry) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    // 토큰 생성
    // role 에 대한 토큰 생성
    private String createAuthToken(String id, String role, Date expiry) {
        return Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    public boolean validate() {
        return this.getTokenClaims() != null;
    }

    // 토큰 인증 예외처리
    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature. JWT 서명이 잘못되었습니다.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT Token. JWT 토큰이 잘못되었습니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token. 만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token. 지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT Token compact of handler are invalid. 처리기의 JWT 토큰 압축이 잘못되었습니다.");
        }
        return null;
    }

    // 만료된 토큰 클레임 가져오기
    public Claims getExpiredTokenClaims() {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token. 만료된 JWT 토큰입니다.");
            return e.getClaims();
        }
        return null;
    }
}
