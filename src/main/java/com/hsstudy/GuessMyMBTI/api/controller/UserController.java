package com.hsstudy.GuessMyMBTI.api.controller;

import com.hsstudy.GuessMyMBTI.api.domain.UserSignUpDTO;
import com.hsstudy.GuessMyMBTI.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDTO userSignUpDTO) throws Exception {
        userService.signUp(userSignUpDTO);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
