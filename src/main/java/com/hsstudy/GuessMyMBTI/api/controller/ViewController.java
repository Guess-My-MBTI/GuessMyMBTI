package com.hsstudy.GuessMyMBTI.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// URL 전달

@Controller // JSON, Page 전달 컨트롤러
@RequiredArgsConstructor
public class ViewController {
    @GetMapping("/kakao-login")
    public String login() {
        return "loginForm";
    }

//    @GetMapping("/login/oauth2/code/kakao")
//    public @ResponseBody String loginCallback(String code) {
//        return code;
//    }
}
