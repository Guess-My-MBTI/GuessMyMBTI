package com.hsstudy.GuessMyMBTI.api.service.explainMbti;

import com.hsstudy.GuessMyMBTI.api.entity.ExplainMbti;
import com.hsstudy.GuessMyMBTI.api.entity.ExplainMbtiDTO;
import com.hsstudy.GuessMyMBTI.api.repository.ExplainMbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExplainMbtiServiceImpl implements ExplainMbtiService{
    @Autowired
    private ExplainMbtiRepository explainMbtiRepository;

    @Override
    public List<ExplainMbtiDTO> getAllExplainMbtis() {
        List<ExplainMbti> explainMbtis = explainMbtiRepository.findAll();
        List<ExplainMbtiDTO> result = new ArrayList<>();

        for (ExplainMbti explainMbti : explainMbtis) {
            result.add(
                    ExplainMbtiDTO.builder()
                            .mbti(explainMbti.getMbti())
                            .name(explainMbti.getName())
                            .charType(explainMbti.getCharType())
                            .build()
            );
        }
        return result;
    }

    @Override
    public List<ExplainMbtiDTO> getOneExplainMbti() {
        List<ExplainMbti> explainMbti = explainMbtiRepository.findOne();

        return null;
    }

}
