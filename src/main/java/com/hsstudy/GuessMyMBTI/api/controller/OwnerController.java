package com.hsstudy.GuessMyMBTI.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController // JSON 형식으로만 넘기는 컨트롤러
public class OwnerController {

    @GetMapping("hello")
    public List<String> hello() {
        return Arrays.asList("안녕하세요 ㅌㅔ스트 중입니다", "Hello");
    }
}
