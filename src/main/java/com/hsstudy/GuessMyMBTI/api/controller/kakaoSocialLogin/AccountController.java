package com.hsstudy.GuessMyMBTI.api.controller.kakaoSocialLogin;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.service.kakao.AccountService;
import com.hsstudy.GuessMyMBTI.api.service.kakao.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @sierrah request header 에 authorization 항목으로 토큰 정보가 들어오면
 * 만료 여부와 유효성을 검사하여 필터링된 정보에 한해 계정 데이터를 제공합니다.
 * <p>
 * 🤔 생각해볼 것 :
 * - 이 컨트롤러 단에서 구현해야 할 기능 : 로그인, 마이페이지, 또?
 * -
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;
    private final SecurityService securityService;

    /* AccountController 단으로 분리해옴 */

    /* 마이페이지 : 쿠키에 저장된 리프레시 토큰으로 정보를 불러옵니다. */
    @GetMapping("/account/mypage")
    public ResponseEntity getCurrentAccountInfo(HttpServletRequest request, @CookieValue(value = "RefreshToken") Cookie cookie) {

        String refreshToken = cookie.getValue();
        HttpHeaders headers = new HttpHeaders();

        Account account = accountService.getAccountInfo(request);
        return ResponseEntity.ok()
                .body(account);
    }

}