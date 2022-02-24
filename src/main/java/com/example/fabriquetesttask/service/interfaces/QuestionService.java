package com.example.fabriquetesttask.service.interfaces;

import com.example.fabriquetesttask.dto.QuestionDto;

public interface QuestionService {
    void addQuestionToSurveyById(Long id, QuestionDto questionDto);
    void deleteQuestionFromSurveyById(Long id, QuestionDto questionDto);
    void updateQuestion(Long id, QuestionDto questionDto);
    QuestionDto getQuestionById(Long id);
}
