package com.hsstudy.GuessMyMBTI.api.service.explainMbti;

import com.hsstudy.GuessMyMBTI.api.repository.ExplainMbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExplainMbtiServiceImpl {
    @Autowired
    private ExplainMbtiRepository explainMbtiRepository;
}
