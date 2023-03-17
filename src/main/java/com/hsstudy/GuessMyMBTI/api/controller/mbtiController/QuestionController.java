package com.hsstudy.GuessMyMBTI.api.controller.mbtiController;


import com.hsstudy.GuessMyMBTI.api.domain.questions.QuestionDTO;
import com.hsstudy.GuessMyMBTI.api.service.question.QuestionService;
import com.hsstudy.GuessMyMBTI.config.jwt.JwtProvider;
import com.hsstudy.GuessMyMBTI.utils.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private JwtProvider jwtTokenProvider;


    @GetMapping("/all")
    private Header<List<QuestionDTO>>getAllQuestions(){
        try {
            return Header.SUCCESS(questionService.getAllQuestions());
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<QuestionDTO>> getQuestionResource(@RequestHeader(name="Authorization") String accessToken) {
//
//        String jwt = accessToken.substring(7); // "Bearer " 이후의 토큰 문자열만 추출
//
//        if (jwtTokenProvider.validateToken(jwt)) {
//            // 토큰이 유효한 경우, 사용자를 인증하고 요청을 처리
//            String username = jwtTokenProvider.getUsernameFromToken(jwt);
//            // ...
//            return ResponseEntity.ok("My resource");
//        } else {
//            // 토큰이 유효하지 않은 경우, 인증 오류 응답 반환
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
}
