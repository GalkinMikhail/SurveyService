package com.example.fabriquetesttask.service.interfaces;

import com.example.fabriquetesttask.dto.*;
import com.example.fabriquetesttask.model.Answer;
import com.example.fabriquetesttask.model.Question;
import com.example.fabriquetesttask.model.Survey;
import com.example.fabriquetesttask.model.User;

import java.util.List;
import java.util.Map;

public interface SurveyService {
    void createSurvey(SurveyCreationDto surveyCreationDto);
    SurveyDto getSurveyById(Long id);
    void deleteSurveyById(Long id);
    void renameSurvey(Long id, SurveyRenameDto surveyRenameDto);
    List<SurveyDto> getAllActiveSurveys();
    void passSurveyById(Long id, SurveyPassDto surveyPassDto);
    List<DetalizationDto> getUserDetalization(List<Answer> list);
}
