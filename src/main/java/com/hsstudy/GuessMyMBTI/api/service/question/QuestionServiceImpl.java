package com.hsstudy.GuessMyMBTI.api.service.question;

import com.hsstudy.GuessMyMBTI.api.domain.questions.Question;
import com.hsstudy.GuessMyMBTI.api.domain.questions.QuestionDTO;
import com.hsstudy.GuessMyMBTI.api.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionDTO> getAllQuestions() {

        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> result = new ArrayList<>();

        for(Question question : questions){
            result.add(
                    QuestionDTO.builder()
                            .id(question.getId())
                            .type(question.getType())
                            .content(question.getContent())
                            .answer1(question.getAnswer1())
                            .answer2(question.getAnswer2())
                            .build()
            );
        }

        return result;
    }
}
