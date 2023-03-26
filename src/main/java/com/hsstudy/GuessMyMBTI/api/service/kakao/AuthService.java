package com.hsstudy.GuessMyMBTI.api.service.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hsstudy.GuessMyMBTI.api.domain.account.KakaoToken;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.LoginResponseDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SetOwnerResultDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SignupRequestDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SignupResponseDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import com.hsstudy.GuessMyMBTI.api.domain.account.RefreshToken;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.kakao.KakaoAccountDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.kakao.KakaoTokenDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.token.TokenDto;
import com.hsstudy.GuessMyMBTI.api.domain.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.exception.CEmailLoginFailedException;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import com.hsstudy.GuessMyMBTI.api.repository.KakaoTokenRepository;
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

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final SecurityService securityService;
    private final KakaoTokenRepository kakaoTokenRepository;
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
        // todo : 이메일은 null 허용을 해야함 선택이라
        Long kakaoId = kakaoAccountDto.getId();

        System.out.println("######## kakaoAccountDto에 담긴 정보 빼내서 Account 클래스에 builder 입력 #########");

        // todo: 처음 로그인인지 아닌지 분기 필요함 -> 해결했나?
        Account existOwner = accountRepository.findById(kakaoId).orElse(null);
        // 처음 로그인이 아닌 경우
        if (existOwner != null) {
            return Account.builder()
                    .id(kakaoAccountDto.getId())
                    .loginType("KAKAO")
                    .email(kakaoAccountDto.getKakao_account().getEmail())
                    .kakaoName(kakaoAccountDto.getKakao_account().getProfile().getNickname())
                    .authority(Authority.ROLE_USER)
                    .mbti(existOwner.getMbti())
                    .result(existOwner.getResult())
                    // todo : 게스트 정보까지 가져와야하나?
                    .build();
        }
        // 처음 로그인 하는 경우
        else {
            return Account.builder()
                    .id(kakaoAccountDto.getId())
                    .loginType("KAKAO")
                    .email(kakaoAccountDto.getKakao_account().getEmail())
                    .kakaoName(kakaoAccountDto.getKakao_account().getProfile().getNickname())
                    .authority(Authority.ROLE_USER)
                    .build();
        }
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
        loginResponseDto.setLoginSuccess(true);
        loginResponseDto.setAccount(account);
        System.out.println("loginResponseDto = " + loginResponseDto);

        // todo : 카카오 엑세스 토큰은 db에만 저장하고 프론트에는 전달하지 않기 -> 일단 약간 해결?
//        loginResponseDto.setKakaoAccessToken(kakaoAccessToken);
//        System.out.println("loginResponseDto = " + loginResponseDto);
        kakaoTokenSave(account.getId() ,kakaoAccessToken);

        Account existOwner = accountRepository.findById(account.getId()).orElse(null);
        try {
            if (existOwner == null) {
                System.out.println("처음 로그인 하는 회원입니다.");
                accountRepository.save(account);
            }
            TokenDto tokenDto = securityService.login(account.getId());
            loginResponseDto.setLoginSuccess(true);
            System.out.println("토큰 발급 성공");

            // 토큰을 프론트로 전달하기
            HttpHeaders headers = setTokenHeaders(tokenDto);
            System.out.println("headers :" + headers.toString());

            return ResponseEntity.ok().headers(headers).body(loginResponseDto);

        } catch (CEmailLoginFailedException e) {
            loginResponseDto.setLoginSuccess(false);
            return ResponseEntity.badRequest().body(loginResponseDto);
        }
    }

    private void kakaoTokenSave(Long id, String kakaoAccessToken) {
        KakaoToken kakaoToken = kakaoTokenRepository.findById(id).orElse(null);
        if (kakaoToken != null) {
            kakaoToken.setKakaoAccessToken(kakaoAccessToken);
        } else {
            KakaoToken newKakaoToken = new KakaoToken(id, kakaoAccessToken);
            kakaoTokenRepository.save(newKakaoToken);
        }
    }

    /* 토큰을 헤더에 배치 */
    public HttpHeaders setTokenHeaders(TokenDto tokenDto) {
        HttpHeaders headers = new HttpHeaders();
        // todo: refresh token 을 프론트의 쿠키로 전달하는게 맞는걸까?
//        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
//                .path("/")
//                .maxAge(60 * 60 * 24 * 7) // 쿠키 유효기간 7일로 설정했음
//                .secure(true)
//                .sameSite("None")
//                .httpOnly(true)
//                .build();
//        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", tokenDto.getAccessToken());

        return headers;
    }

    public ResponseEntity<SetOwnerResultDto> ownerResultSave(@RequestBody SetOwnerResultDto requestDto) {
        System.out.println("requestDto.getId() = " + requestDto.getId());
        Account existOwner = accountRepository.findById(requestDto.getId()).orElse(null);
        System.out.println("existOwner = " + existOwner.getId() + " " + existOwner.getEmail());
        existOwner.setMbti(requestDto.getMbti());
        existOwner.setResult(requestDto.getResult());
        accountRepository.save(existOwner);

        SetOwnerResultDto setOwnerResultDto = new SetOwnerResultDto();

        return ResponseEntity.ok().body(setOwnerResultDto);
    }


    public String share(HttpServletRequest request) {
        String ownerId = request.getParameter("id");

        StringBuilder sb = new StringBuilder();
        sb.append("https://localhost:3000/guest-login?id=");
        sb.append(ownerId);

        return sb.toString();
    }

    public ResponseEntity<String> mainPage(HttpServletRequest request) throws JsonProcessingException {
        Long id = Long.parseLong(request.getParameter("id"));
        Account account = accountRepository.findById(id).orElse(null);

        // todo : guest가 결과 완료하지 않았따면 출력하지 않기 -> 일단 약간 해결 완?
        if (account == null) {
            // 예외 처리 등

        }

        List<Guest> guests = account.getGuests();

        if (guests == null) {
            // 예외 처리 등
            return null;
        }

        // guest 완료한사람만 출력하기
        ArrayList<Guest> guestList = new ArrayList<>();
        for (Guest guest : guests) {
            if (guest.getResult() != null) {
                guestList.add(guest);
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("account", account);
        resultMap.put("guests", guestList);

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(resultMap);

        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity<String> kakaoLogout(Long ownerId) {
        KakaoToken kakaoUser = kakaoTokenRepository.findById(ownerId).orElse(null);
        String kakaoAccessToken = kakaoUser.getKakaoAccessToken();

        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoLogout = new HttpEntity<>(headers);

        // POST 방식으로 API 서버에 요청 보내고, response 받아옴
        ResponseEntity<String> logoutResponse = rt.exchange(
                "https://kapi.kakao.com/v1/user/logout",
                HttpMethod.POST,
                kakaoLogout,
                String.class
        );

        return logoutResponse;
    }


    public ResponseEntity<String> deleteGuest(Long id) throws JsonProcessingException {

        Account account = accountRepository.findById(id).orElse(null);
        System.out.println("kakaoUser: " + account);
        System.out.println("kakaoUser.getGuests: " + account.getGuests());

        if (account == null) {
            // 예외 처리 등

        } else {
            account.setMbti(null);
        }

        List<Guest> guests = account.getGuests();

        for (Guest guest : guests) {
            guest.setOwner(null);
            guestRepository.deleteById(guest.getId());
        }

        accountRepository.save(account);

        String result = "삭제 완료";
        System.out.println(result);

        return ResponseEntity.ok().body(result);
    }

}