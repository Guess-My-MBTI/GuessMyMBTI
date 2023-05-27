package com.hsstudy.GuessMyMBTI.api.controller.kakaoSocialLogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsstudy.GuessMyMBTI.api.domain.account.KakaoToken;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.LoginResponseDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SetOwnerResultDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SignupRequestDto;
import com.hsstudy.GuessMyMBTI.api.domain.account.dto.SignupResponseDto;
import com.hsstudy.GuessMyMBTI.api.service.kakao.AuthService;
import com.hsstudy.GuessMyMBTI.api.service.kakao.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @sierrah OAuth Kakao 인증 관련 요청을 처리하는 API 입니다.
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://gmmclients.click",allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;
    private final SecurityService securityService;

    @GetMapping("/aws")
    public ResponseEntity awsGet() {
        return ResponseEntity.ok(200);
    }
    // todo : 로그인 회원 정보 더 담기
    @GetMapping("/login/oauth2/callback/kakao")
    public ResponseEntity<LoginResponseDto> kakaoLogin(HttpServletRequest request) {

        String code = request.getParameter("code");
        String kakaoAccessToken = authService.getKakaoAccessToken(code).getAccess_token();
        return authService.kakaoLogin(kakaoAccessToken);
    }

    // todo : 로그아웃 수정하기
    @PostMapping("/owner/logout/{id}")
    public ResponseEntity<String> kakaoLogout(@PathVariable("id") Long id) {
        return authService.kakaoLogout(id);
    }

    // todo : owner의 mbti 결과 -> EEEEJJJJ... , ESFJ 이거 두개 전달용
    @PostMapping("/owner-result")
    public ResponseEntity<SetOwnerResultDto> ownerResultSave(@RequestBody SetOwnerResultDto requestDto) {
        return authService.ownerResultSave(requestDto);
    }

    @GetMapping("/share")
    public String share(HttpServletRequest request) {
        return authService.share(request);
    }

    @GetMapping("/main-page")
    public ResponseEntity<String> mainPage(HttpServletRequest request) throws JsonProcessingException {
        return authService.mainPage(request);
    }

    // todo : 오너가 다시하기를 하면, 게스트 데이터베이스에서 오너 아이디를 가진 게스트들 모두 삭제, 오너의 mbti와 result도 Null값으로 변경
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") Long id) throws JsonProcessingException {
        return authService.deleteGuest(id);
    }
}