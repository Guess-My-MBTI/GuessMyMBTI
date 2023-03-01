package com.hsstudy.GuessMyMBTI.api.controller;


import com.hsstudy.GuessMyMBTI.api.entity.question.QuestionDTO;
import com.hsstudy.GuessMyMBTI.api.service.question.QuestionService;
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

    @GetMapping("/all")
    private Header<List<QuestionDTO>>getAllQuestions(){
        try {
            return Header.SUCCESS(questionService.getAllQuestions());
        }catch (Exception e){
            return Header.FAIL(e);
        }
    }
}
