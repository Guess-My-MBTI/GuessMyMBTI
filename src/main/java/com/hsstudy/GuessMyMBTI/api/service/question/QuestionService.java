package com.hsstudy.GuessMyMBTI.api.service.question;


import com.hsstudy.GuessMyMBTI.api.domain.questions.QuestionDTO;

import java.util.List;


public interface QuestionService {

    List<QuestionDTO> getAllQuestions();

}
