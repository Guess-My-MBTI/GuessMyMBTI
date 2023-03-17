package com.hsstudy.GuessMyMBTI.api.repository;


import com.hsstudy.GuessMyMBTI.api.domain.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM Question", nativeQuery = true)
    List<Question> findAll();
}
