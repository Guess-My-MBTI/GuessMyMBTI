package com.hsstudy.GuessMyMBTI.api.controller;

import com.hsstudy.GuessMyMBTI.api.service.explainMbti.ExplainMbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result")
public class ExplainMbtiController {

    @Autowired
    private ExplainMbtiService explainMbtiService;



}
