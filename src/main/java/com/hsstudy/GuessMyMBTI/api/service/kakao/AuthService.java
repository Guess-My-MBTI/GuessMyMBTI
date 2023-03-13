package com.hsstudy.GuessMyMBTI.api.service.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hsstudy.GuessMyMBTI.api.domain.dto.AccountDto;
import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.entity.guest.GuestDto;
import com.hsstudy.GuessMyMBTI.api.domain.Account;
import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import com.hsstudy.GuessMyMBTI.api.domain.RefreshToken;
import com.hsstudy.GuessMyMBTI.api.domain.dto.LoginResponseDto;
import com.hsstudy.GuessMyMBTI.api.domain.dto.SignupRequestDto;
import com.hsstudy.GuessMyMBTI.api.domain.dto.SignupResponseDto;
import com.hsstudy.GuessMyMBTI.api.domain.dto.kakao.KakaoAccountDto;
import com.hsstudy.GuessMyMBTI.api.domain.dto.kakao.KakaoTokenDto;
import com.hsstudy.GuessMyMBTI.api.domain.dto.token.TokenDto;
import com.hsstudy.GuessMyMBTI.api.exception.CEmailLoginFailedException;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import com.hsstudy.GuessMyMBTI.api.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

/**
 * @sierrah 카카오 로그인 로직을 처리합니다.
 * login
 * signup
 * reissue
 * getKakaoAccessToken
 * getKakaoInfo
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository tokenRepository;
    private final SecurityService securityService;
    private final GuestRepository guestRepository;

    /* 환경변수 가져오기 */
    @Value("${spring.security.oauth2.client.registration.kakao.clientId}")
    String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirectUri}")
    String KAKAO_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.tokenUri}")
    String KAKAO_TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.userInfoUri}")
    String KAKAO_USER_INFO_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.clientSecret}")
    String KAKAO_CLIENT_SECRET;

    /* 인가코드로 kakaoAccessToken 따오는 메소드 */
    @Transactional
    public KakaoTokenDto getKakaoAccessToken(String code) {
        System.out.println("################ AuthService 실행 ##################");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Http Body 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); //카카오 공식문서 기준 authorization_code 로 고정
        params.add("client_id", KAKAO_CLIENT_ID); //카카오 앱 REST API 키
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code", code); //인가 코드 요청시 받은 인가 코드값, 프론트에서 받아오는 그 코드
        params.add("client_secret", KAKAO_CLIENT_SECRET);

        // 헤더와 바디 합치기 위해 Http Entity 객체 생성
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        System.out.println("AuthService -> kakaoTokenRequest : " + kakaoTokenRequest);

        // 카카오로부터 Access token 수신
        RestTemplate rt = new RestTemplate(); //통신용
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                KAKAO_TOKEN_URI,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println("accessTokenResponse = " + accessTokenResponse);

        // JSON Parsing (-> KakaoTokenDto)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        KakaoTokenDto kakaoTokenDto = null;
        try {
            kakaoTokenDto = objectMapper.readValue(accessTokenResponse.getBody(), KakaoTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("kakaoTokenDto = " + kakaoTokenDto);

        return kakaoTokenDto; // 리턴 성공은 함 아래 getKakaoInfo에서 에러 발생
    }

    /* kakaoAccessToken 으로 카카오 서버에 정보 요청 */
    public Account getKakaoInfo(String kakaoAccessToken) { // 토큰은 문제없이 잘 전달 됨
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> accountInfoRequest = new HttpEntity<>(headers);
        System.out.println("################# getKakaoInfo 메소드 실행 ###################");
        System.out.println("accountInfoRequest = " + accountInfoRequest);

        // POST 방식으로 API 서버에 요청 보내고, response 받아옴
        ResponseEntity<String> accountInfoResponse = rt.exchange(
                KAKAO_USER_INFO_URI,
                HttpMethod.POST,
                accountInfoRequest,
                String.class
        );
        System.out.println("수행 완료 : accountInfoResponse = " + accountInfoResponse);
        System.out.println("카카오 서버에서 정상적으로 데이터를 수신했습니다.");

        // JSON Parsing (-> kakaoAccountDto)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        KakaoAccountDto kakaoAccountDto = null;
        try {
            System.out.println("Dto 읽어오기");
            kakaoAccountDto = objectMapper.readValue(accountInfoResponse.getBody(), KakaoAccountDto.class);
            System.out.println("Dto 읽어오기 성공 kakaoAccountDto :" + kakaoAccountDto);
        } catch (JsonProcessingException e) {
            System.out.println("에러 발생");
            e.printStackTrace();
        }

        // kakaoAccountDto 에서 필요한 정보 꺼내서 Account 객체로 매핑
        String email = kakaoAccountDto.getKakao_account().getEmail();
        String kakaoName = kakaoAccountDto.getKakao_account().getProfile().getNickname();
        System.out.println("######## kakaoAccountDto에 담긴 정보 빼내서 Account 클래스에 builder 입력 #########");
        System.out.println("email: " + email + " kakaoName: " + kakaoName);

        return Account.builder()
                .loginType("KAKAO")
                .email(email)
                .kakaoName(kakaoName)
                .authority(Authority.ROLE_USER)
                .build();
    }

    /* login 요청 보내는 회원가입 유무 판단해 분기 처리 */
    public ResponseEntity<LoginResponseDto> kakaoLogin(String kakaoAccessToken) { // 토큰은 잘 전달됐음
        // kakaoAccessToken 으로 회원정보 받아오기
        Account account = getKakaoInfo(kakaoAccessToken); // 제일 처음 수행하는 부분에서 에러가 발생했음
        System.out.println("빌더로 생성한 account :" +
                account.getKakaoName() + " " +
                account.getNickname() + " " +
                account.getLoginType() + " " +
                account.getEmail() + " " +
                account.getAuthority() + " " +
                account.getId());
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setKakaoAccessToken(kakaoAccessToken);
        System.out.println("loginResponseDto = " + loginResponseDto);
        loginResponseDto.setAccount(account);
        System.out.println("loginResponseDto = " + loginResponseDto);
        try {
            System.out.println("AccountRepository에 email로 유저 있는지 판단하기");
            Account existAccount = accountRepository.findByEmail(account.getEmail()).orElse(null);
            if (existAccount == null) {
                System.out.println("처음 로그인 하는 회원입니다.");
                accountRepository.save(account);
            }
            TokenDto tokenDto = securityService.login(account.getEmail());
            loginResponseDto.setLoginSuccess(true);
            System.out.println("토큰 발급 성공");
            HttpHeaders headers = setTokenHeaders(tokenDto);
            System.out.println("headers :" + headers.toString());
            return ResponseEntity.ok().headers(headers).body(loginResponseDto);

        } catch (CEmailLoginFailedException e) {
            loginResponseDto.setLoginSuccess(false);
            return ResponseEntity.ok(loginResponseDto);
        }
    }

    /* 토큰을 헤더에 배치 */
    public HttpHeaders setTokenHeaders(TokenDto tokenDto) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
                .path("/")
                .maxAge(60 * 60 * 24 * 7) // 쿠키 유효기간 7일로 설정했음
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", tokenDto.getAccessToken());

        return headers;
    }

    /* 회원가입 요청 처리 */
    public ResponseEntity<SignupResponseDto> kakaoSignup(@RequestBody SignupRequestDto requestDto) {
        // 받아온 정보 DB에 저장
        Account newAccount = Account.builder()
                .loginType("kakao")
                .authority(Authority.ROLE_USER)
                .email(requestDto.getAccount().getEmail())
                .kakaoName(requestDto.getAccount().getKakaoName())
                .nickname(requestDto.getNickname())
                .picture(requestDto.getPicture())
                .build();
        accountRepository.save(newAccount);

        // 회원가입 상황에 대해 토큰을 발급하고 헤더와 쿠키에 배치
        TokenDto tokenDto = securityService.signup(requestDto);
        saveRefreshToken(newAccount, tokenDto);
        HttpHeaders headers = setTokenHeaders(tokenDto);

        // 응답 작성
        SignupResponseDto responseDto = new SignupResponseDto();
        responseDto.setAccount(accountRepository.findByEmail(requestDto.getAccount().getEmail())
                .orElseThrow(CEmailLoginFailedException::new));
        responseDto.setResult("회원가입이 완료되었습니다.");
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    public ResponseEntity<AccountDto> ownerResultSave(@RequestBody AccountDto requestDto) {
        // 받아온 정보 DB에 저장
        // repo에서 누구인지 찾기
        Account existOwner = accountRepository.findById(requestDto.getId()).orElse(null);
        System.out.println("existOwner = " + existOwner.getEmail());
        existOwner.setMbti(requestDto.getMbti());
        existOwner.setResult(requestDto.getResult());
        accountRepository.save(existOwner);

        HttpHeaders headers = new HttpHeaders();
        headers.add("result", requestDto.getResult());
        headers.add("mbti", requestDto.getMbti());

        return ResponseEntity.ok().headers(headers).body(null);
    }

    /* Refresh Token 을 Repository 에 저장하는 메소드 */
    public void saveRefreshToken(Account account, TokenDto tokenDto) {
        RefreshToken refreshToken = RefreshToken.builder()
                .key(account.getId())
                .token(tokenDto.getRefreshToken())
                .build();
        tokenRepository.save(refreshToken);
        System.out.println("토큰 저장이 완료되었습니다 : 계정 아이디 - " + account.getId() + ", refresh token - " + tokenDto.getRefreshToken());
    }

//    /* 회원가입 */
//    public Long kakaoSignUp(SignupRequestDto requestDto) {
//
//        KakaoAccountDto kakaoAccountDto = getKakaoInfo(requestDto.getKakaoAccessToken());
//        Account account = mapKakaoInfo(kakaoAccountDto);
//
//        // 닉네임, 프로필사진 set
//        String nickname = requestDto.getAccountName();
//        String accountPicture = requestDto.getPicture();
//        account.setNickname(nickname);
//        account.setPicture(accountPicture);
//
//        // save
//        accountRepository.save(account);
//
//        // 회원가입 결과로 회원가입한 accountId 리턴
//        return account.getId();
//    }
//
//    public Account accountFindById(Long id) {
//        return accountRepository.findById(id)
//                .orElseThrow(CUserNotFoundException::new);
//    }
}