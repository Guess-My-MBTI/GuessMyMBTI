package com.hsstudy.GuessMyMBTI.api.controller;

import com.hsstudy.GuessMyMBTI.api.entity.ExplainMbtiDTO;
import com.hsstudy.GuessMyMBTI.api.entity.question.QuestionDTO;
import com.hsstudy.GuessMyMBTI.api.service.explainMbti.ExplainMbtiService;
import com.hsstudy.GuessMyMBTI.utils.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ExplainMbtiController {

    @Autowired
    private ExplainMbtiService explainMbtiService;

    @GetMapping("/all")
    private Header<List<ExplainMbtiDTO>> getAllExplainMbtis() {
        try {
            return Header.SUCCESS((explainMbtiService.getAllExplainMbtis()));
        } catch (Exception e) {
            return Header.FAIL(e);
        }
    }

    @GetMapping("/one/{mbti}")
    private Header<List<ExplainMbtiDTO>> getOneExplainMbti() {
        try {
            return Header.SUCCESS(explainMbtiService.getOneExplainMbti());
        } catch (Exception e) {
            return Header.FAIL(e);
        }
    }

}
