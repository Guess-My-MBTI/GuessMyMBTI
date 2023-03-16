package com.hsstudy.GuessMyMBTI.config.jwt;

import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import com.hsstudy.GuessMyMBTI.api.domain.dto.token.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {
//
//    @Value("${jwt.secret}")
//    private byte[] secretKey;
//
//    private String ROLES = "roles";
//    private final Long accessTokenValidMillisecond = 60 * 60 * 1000L; // 1 hour
//    private final Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day
//    private final UserDetailsService userDetailsService;
//
//    /* Jwt 생성 */
//    // 권한정보를 리스트가 아니라 단독 String 으로 가게 바꿨음
//    public TokenDto createTokenDto(Long accountId, String roles) {
//
//        // Claims 에 user 구분을 위한 식별자 및 authorities 목록 삽입
//        Claims claims = Jwts.claims().setSubject(String.valueOf(accountId));
//        claims.put(ROLES, roles);
//
//        // 생성날짜, 만료날짜를 위한 Date
//        Date now = new Date();
//
//        String accessToken = Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//
//        String refreshToken = Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//
//        return TokenDto.builder()
//                .grantType("Bearer")
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .accessTokenExpireDate(accessTokenValidMillisecond)
//                .build();
//    }
//
//    // Jwt 로 인증정보를 조회
//    public Authentication getAuthentication(String token) {
//
//        // Jwt 에서 claims 추출
//        Claims claims = parseClaims(token);
//
//        // 권한 정보가 없음
//        if (claims.get(ROLES) == null) {
//            throw new CAuthenticationEntryPointException();
//        }
//
//        // accountId로 유저 조회
//        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    // Jwt 토큰 복호화해서 가져오기
////    private Claims parseClaims(String token) {
////
////        try {
////            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
////        } catch (ExpiredJwtException e) {
////            return e.getClaims();
////        }
////    }
//
//    // HTTP Request 의 Header 에서 Token Parsing
//    public String resolveToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        return token;
//    }
//
//    // jwt 의 유효성 및 만료일자 확인
//    public boolean validationToken(String token) {
//
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (SecurityException | MalformedJwtException e) {
//            log.error("잘못된 Jwt 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            log.error("만료된 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            log.error("지원하지 않는 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            log.error("잘못된 토큰입니다.");
//        }
//        return false;
//    }

    /* ---------------------- 토큰 발급 수정 필드 ------------------------- */
    private final Key key;
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //access 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //refresh 7일
    /* ---------------------- 토큰 발급 수정 메서드 ------------------------- */

    public JwtProvider(@Value("${jwt.secretKey}") String secretKey) {
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(String email) {

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(email) //payload "sub" : "name"
                .claim(AUTHORITIES_KEY, Authority.ROLE_USER) //payload "auth" : "ROLE_USER"
                .setExpiration(accessTokenExpiresIn) //payload "exp" : 1234567890 (10자리)
                .signWith(key, SignatureAlgorithm.HS512) //header "alg" : HS512 (해싱 알고리즘 HS512)
                .compact();

        // Refresh Token 생성
        // refresh~에는 claim 없이 만료시간만 담아줌
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {

        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임으로 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /* 토큰 유효성 검증, boolean */
    public boolean validateToken(String token) {

        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("JWT 서명의 형식이 잘못되었습니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("걍.. 잘못된 JWT 입니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}