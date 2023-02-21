package com.hsstudy.GuessMyMBTI.api.repository;

import com.hsstudy.GuessMyMBTI.api.entity.ExplainMbti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExplainMbtiRepository extends JpaRepository<ExplainMbti, Long> {

    List<ExplainMbti> findAll();

}

