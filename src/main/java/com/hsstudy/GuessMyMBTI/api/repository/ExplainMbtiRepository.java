package com.hsstudy.GuessMyMBTI.api.repository;

import com.hsstudy.GuessMyMBTI.api.domain.explainMbti.ExplainMbti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExplainMbtiRepository extends JpaRepository<ExplainMbti, Long> {

    @Query(value = "SELECT * FROM ExplainMbti", nativeQuery = true)
    List<ExplainMbti> findAll();

}

