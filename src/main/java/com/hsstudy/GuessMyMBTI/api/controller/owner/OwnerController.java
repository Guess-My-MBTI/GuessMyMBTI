package com.hsstudy.GuessMyMBTI.api.controller.owner;

import com.hsstudy.GuessMyMBTI.api.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
