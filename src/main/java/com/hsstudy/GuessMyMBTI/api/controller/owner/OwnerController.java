package com.hsstudy.GuessMyMBTI.api.controller.owner;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.hsstudy.GuessMyMBTI.api.service.oauth.KakaoOwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController // JSON 형식으로만 넘기는 컨트롤러
public class OwnerController {

    @GetMapping("hello")
    public List<String> hello() {
        return Arrays.asList("안녕하세요 ㅌㅔ스트 중입니다", "Hello");
    }

//    // 카카오 회원가입
//    @GetMapping("/login/oauth2/callback/kakao")
//    public Long kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
//        // code : 카카오 서버로부터 받은 인가 코드
//        SignupSocialDto signupKakaoDto = KakaoOwnerService.kakaoLogin(code);
//        response.addHeader(AUTH_HEADER, signupKakaoDto.getToken());
//
//        return signupKakaoDto.getUserId();
//    }
}
