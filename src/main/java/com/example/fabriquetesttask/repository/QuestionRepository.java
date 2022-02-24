package com.example.fabriquetesttask.repository;

import com.example.fabriquetesttask.model.Enums.QuestionType;
import com.example.fabriquetesttask.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByTextAndType(String text, QuestionType type);
}
