package com.hsstudy.GuessMyMBTI.api.service.question;


import com.hsstudy.GuessMyMBTI.api.entity.question.Question;
import com.hsstudy.GuessMyMBTI.api.entity.question.QuestionDTO;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuestionService {

    List<QuestionDTO> getAllQuestions();

}
