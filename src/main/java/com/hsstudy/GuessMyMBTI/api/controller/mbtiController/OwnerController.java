package com.hsstudy.GuessMyMBTI.api.controller.mbtiController;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.hsstudy.GuessMyMBTI.api.service.oauth.KakaoOwnerService;
import com.hsstudy.GuessMyMBTI.api.entity.owner.OwnerDTO;
import com.hsstudy.GuessMyMBTI.api.service.owner.OwnerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController // JSON 형식으로만 넘기는 컨트롤러
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/oauth2/token")
    public List<String> jwtTest() {
        return Arrays.asList("안녕하세요 jwt 테스트 중입니다", "jwt test");
    }

    @PostMapping("/owner/result")
    public void saveQuestion(@RequestBody OwnerRequest request) {
        ownerService.save(request);
    }

    @Data
    public static class OwnerRequest{
        private String answer;
        private String ownerMbti;
    }

    // 카카오 회원가입
//    @GetMapping("/login/oauth2/callback/kakao")
//    public Long kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
//        // code : 카카오 서버로부터 받은 인가 코드
//        OwnerDTO ownerDTO = OwnerService.kakaoLogin(code);
//        response.addHeader(AUTH_HEADER, ownerDTO.getToken());
//
//        return ownerDTO.getUserId();
//    }
}
