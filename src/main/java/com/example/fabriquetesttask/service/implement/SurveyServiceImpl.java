package com.example.fabriquetesttask.service.implement;

import com.example.fabriquetesttask.dto.*;
import com.example.fabriquetesttask.exception.ResourceNotFoundException;
import com.example.fabriquetesttask.mapper.SurveyMapper;
import com.example.fabriquetesttask.model.Answer;
import com.example.fabriquetesttask.model.Question;
import com.example.fabriquetesttask.model.Survey;
import com.example.fabriquetesttask.model.User;
import com.example.fabriquetesttask.repository.AnswerRepository;
import com.example.fabriquetesttask.repository.QuestionRepository;
import com.example.fabriquetesttask.repository.SurveyRepository;
import com.example.fabriquetesttask.repository.UserRepository;
import com.example.fabriquetesttask.security.SecurityUser;
import com.example.fabriquetesttask.service.interfaces.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public void createSurvey(SurveyCreationDto surveyCreationDto) {
        SecurityUser myUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = myUser.getUsername();
        User user = userRepository.findByUsername(username);
        Survey survey = new Survey();
        survey.setTitle(surveyCreationDto.getTitle());
        survey.setDescription(surveyCreationDto.getDescription());
        survey.setStartDate(LocalDateTime.now());
        survey.setExpirationDate(surveyCreationDto.getExpirationDate());
        survey.setCreatorId(user);
        surveyRepository.save(survey);
    }

    @Override
    public SurveyDto getSurveyById(Long id) {
        return surveyToSurveyDto(surveyRepository.getById(id));
    }

    @Override
    public void deleteSurveyById(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public void renameSurvey(Long id, SurveyRenameDto surveyRenameDto) {
        Survey survey = surveyRepository.getById(id);

        if (survey == null){
            throw new ResourceNotFoundException("Survey with id " + id + " not found", "survey id");
        }
        survey.setTitle(surveyRenameDto.getTitle());
        surveyRepository.save(survey);
    }

    @Override
    public List<SurveyDto> getAllActiveSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        surveys.removeIf(n -> n.getExpirationDate().isBefore(LocalDateTime.now()));
        return allToDto(surveys);
    }

    @Override
    public void passSurveyById(Long id, SurveyPassDto surveyPassDto) {
        Survey survey = surveyRepository.getById(id);
        if (survey == null){
            throw new ResourceNotFoundException("Survey with id " + id + " not found", "survey id");
        }
        Map<Long, String> answers = surveyPassDto.getAnswers();
        User user;
        if (surveyPassDto.getFlag().equals("anonymously")){
            user = userRepository.findByUsername("anonymous");
        }
        else {
            user = userRepository.getById(surveyPassDto.getUserId());
        }
        for (Map.Entry<Long,String> entry : answers.entrySet()){
                Question question = questionRepository.getById(entry.getKey());
                Answer answerToCreate = new Answer();
                answerToCreate.setAnswer(entry.getValue());
                answerToCreate.setSurveyId(survey);
                answerToCreate.setUser(user);
                answerToCreate.setQuestionId(question);
                answerRepository.save(answerToCreate);
        }
    }

    @Override
    public List<DetalizationDto> getUserDetalization(List<Answer> answers) {
        Map<SurveyDto,List<QuestionDetalizationDto>> map = new HashMap<>();
        for (Answer answer : answers) {
            Survey survey = answer.getSurveyId();
            SurveyDto key = surveyToSurveyDto(survey);
            QuestionDetalizationDto value = new QuestionDetalizationDto(
                    answer.getQuestionId().getText(),
                    answer.getQuestionId().getType().toString(),
                    answer.getAnswer());
            if (map.containsKey(key)){
                map.get(key).add(value);
            }
            else {
                map.put(key,new ArrayList<>(List.of(value)));
            }
        }
        return map.keySet().stream()
                .map(key -> new DetalizationDto(key.getTitle(), key.getDescription(), key.getExpirationDate(), map.get(key)))
                .collect(Collectors.toList());
    }


    private SurveyDto surveyToSurveyDto(Survey survey){
        return SurveyMapper.SURVEY_MAPPER.surveyToSurveyDto(survey);
    }
    private List<SurveyDto> allToDto(List<Survey> surveys){
        return SurveyMapper.SURVEY_MAPPER.allToDto(surveys);
    }
}
