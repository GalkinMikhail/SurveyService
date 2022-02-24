package com.example.fabriquetesttask.repository;

import com.example.fabriquetesttask.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @Query(value = "from Answer a where a.user.id = ?1")
    List<Answer> finAllByUserId(Long id);
}
