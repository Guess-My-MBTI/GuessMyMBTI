package com.hsstudy.GuessMyMBTI.api.service.kakao;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.account.RefreshToken;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SignupRequestDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.token.TokenDto;
import com.hsstudy.GuessMyMBTI.api.exception.CEmailLoginFailedException;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import com.hsstudy.GuessMyMBTI.api.repository.RefreshTokenRepository;
import com.hsstudy.GuessMyMBTI.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/*

토큰을 발급하는 모든 상황을 처리하는 Service

*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AccountRepository accountRepository;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository tokenRepository;

//
//    /* 로그인 된 사용자에게 토큰 발급 : refresh token 은 DB 에 저장 */
//    public TokenDto login(Long accountId) {
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(CEmailLoginFailedException::new);
//        System.out.println("SecurityService-login: 계정을 찾았습니다. " + account);
//        // 토큰 발행
////        List<String> Role = Collections.singletonList("USER");
//        TokenDto tokenDto = jwtProvider.generateTokenDto(account.getId(), "USER");
//
//        // RefreshToken 만 DB에 저장
//        // signup 시에도 저장하고, 로그인시에도 저장하므로 존재하는 토큰을 찾기 위해 key 값이 필요
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(account.getId())
//                .token(tokenDto.getRefreshToken())
//                .build();
//        tokenRepository.save(refreshToken);
//        System.out.println("토큰 발급과 저장을 완료했습니다.");
//        return tokenDto;
//    }
//
//    /* 회원 정보를 저장한 뒤 해당 회원 정보로 토큰 발급 */
//    public TokenDto signup(Long accountId) {
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(CUserNotFoundException::new);
//        TokenDto tokenDto = jwtProvider.createTokenDto(account.getId(), "USER");
//
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(accountId)
//                .token(tokenDto.getRefreshToken())
//                .build();
//        tokenRepository.save(refreshToken);
//
//        return tokenDto;
//    }
//
//    /* 토큰의 유효성을 검사한 뒤 토큰 재발급 */
//    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
//
//        // refreshToken 만료 검사
//        if (!jwtProvider.validationToken(tokenRequestDto.getRefreshToken())) {
//            throw new CRefreshTokenException();
//        }
//
//        // AccessToken 으로 accountId 가져오기
//        String accessToken = tokenRequestDto.getAccessToken();
//        Authentication authentication = jwtProvider.getAuthentication(accessToken);
//
//        // accountId 로 계정 정보와 refresh token 검색 -> 없을 경우 에러 발생
//        Long accountId = Long.parseLong(authentication.getName());
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(CUserNotFoundException::new);
//        RefreshToken refreshToken = tokenRepository.findByKey(accountId)
//                .orElseThrow(CRefreshTokenException::new);
//
//        // 가져온 refreshToken 이 들어온 refreshToken 과 일치하지 않을 경우 에러 발생
//        if (!refreshToken.getToken().equals(tokenRequestDto.getRefreshToken()))
//            throw new CRefreshTokenException();
//
//        // 문제없으면 토큰 재발행
//        TokenDto newToken = jwtProvider.createTokenDto(accountId, "USER");
//        refreshToken.setToken(newToken.getRefreshToken());
//        tokenRepository.save(refreshToken);
//
//        return newToken;
//    }
//
//    public RefreshResponseDto refresh(String refreshToken) {
//        RefreshResponseDto responseDto = new RefreshResponseDto();
//        // refreshToken 만료 검사
//        if (!jwtProvider.validationToken(refreshToken)) {
//            throw new CRefreshTokenException();
//        }
//
//        // refreshToken 이 DB에 있으면 access Token 재발급
//        RefreshToken savedRefreshToken = tokenRepository.findByToken(refreshToken)
//                .orElseThrow(CUserNotFoundException::new);
//
//        Long accountId = savedRefreshToken.getKey();
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(CUserNotFoundException::new);
//
//        // 가져온 refreshToken 이 들어온 refreshToken 과 일치하지 않을 경우 에러 발생
//        if (!refreshToken.equals(savedRefreshToken.getToken()))
//            throw new CRefreshTokenException();
//
//        // 문제없으면 access token 만 재발행
//        TokenDto newToken = jwtProvider.createTokenDto(accountId, "USER");
//        responseDto.setAccount(account);
//        responseDto.setAccessToken(newToken.getAccessToken());
////        refreshToken.setToken(newToken.getRefreshToken());
////        tokenRepository.save(refreshToken); // 재발행이므로 refreshToken을 저장하지 않음
//
//        return responseDto;
//    }

    /* ---------------------- 토큰 발급 수정 메서드 ------------------------- */
    public static Long getCurrentAccountId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }

    /* 로그인 된 사용자에게 토큰 발급 : refresh token 은 DB 에 저장 */
    public TokenDto login(String email) {
        // 문제 발생 지점 -> 당연히 accountRepo에 정보가 없으니 문제 발생.
        System.out.println("TokenDto login의 문제 발생 지점 email :" + email);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(CEmailLoginFailedException::new);
        System.out.println("SecurityService-login: 계정을 찾았습니다. " + account);

        // 토큰 발행
        TokenDto tokenDto = jwtProvider.generateTokenDto(email);
        System.out.println("토큰 발행 ..... :" + tokenDto);

        // RefreshToken 만 DB에 저장
        // signup 시에도 저장하고, 로그인시에도 저장하므로 존재하는 토큰을 찾기 위해 key 값이 필요
        RefreshToken refreshToken = RefreshToken.builder()
                .key(account.getId())
                .token(tokenDto.getRefreshToken())
                .build();
        tokenRepository.save(refreshToken);
        System.out.println("토큰 발급과 저장을 완료했습니다.");
        return tokenDto;
    }

    /**
     * 회원가입 요청에 대해 Access Token 과 Refresh Token 을 발급하고,
     * Refresh Token 을 Token Repository 에 저장합니다.
     * try: account 가 저장되지 않은 상태에서 id 호출 불가능
     * -> refreshToken save 를 auth 단으로 올릴 것인가?
     */
    public TokenDto signup(SignupRequestDto requestDto) {
        Account account = requestDto.getAccount();
        return jwtProvider.generateTokenDto(account.getEmail());
    }
}