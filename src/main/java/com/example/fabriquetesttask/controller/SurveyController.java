package com.example.fabriquetesttask.controller;

import com.example.fabriquetesttask.controller.urls.Urls;
import com.example.fabriquetesttask.dto.*;
import com.example.fabriquetesttask.exception.ResourceNotFoundException;
import com.example.fabriquetesttask.model.Answer;
import com.example.fabriquetesttask.service.interfaces.AnswerService;
import lombok.RequiredArgsConstructor;
import com.example.fabriquetesttask.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.fabriquetesttask.service.interfaces.QuestionService;
import com.example.fabriquetesttask.service.interfaces.SurveyService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Urls.Survey.FULL)
    public ResponseEntity<String> createSurvey(@RequestBody SurveyCreationDto surveyCreationDto){
        this.surveyService.createSurvey(surveyCreationDto);
        return new ResponseEntity<>("Survey created successfully", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Urls.Survey.id.FULL)
    public ResponseEntity<String> renameSurvey(@RequestBody SurveyRenameDto surveyRenameDto, @PathVariable Long id){
        this.surveyService.renameSurvey(id,surveyRenameDto);
        return new ResponseEntity<>("Survey renamed successfully",HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(Urls.Survey.id.FULL)
    public ResponseEntity<String> deleteSurvey(@PathVariable Long id){
        this.surveyService.deleteSurveyById(id);
        return new ResponseEntity<>("Survey deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping(Urls.Survey.FULL)
    public ResponseEntity<List<SurveyDto>> getAllActiveSurveys(){
        List<SurveyDto> surveyList = this.surveyService.getAllActiveSurveys();

        if (surveyList == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(surveyList,HttpStatus.OK);
    }

    @GetMapping(Urls.Survey.id.FULL)
    public ResponseEntity<SurveyDto> getSurvey(@PathVariable Long id){
        SurveyDto surveyDto = surveyService.getSurveyById(id);

        if (surveyDto == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(surveyDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(Urls.Survey.question.id.FULL)
    public ResponseEntity<String> addQuestionToSurvey(@RequestBody QuestionDto questionDto, @PathVariable Long id){
        this.questionService.addQuestionToSurveyById(id, questionDto);
        return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(Urls.Survey.question.id.FULL)
    public ResponseEntity<String> deleteQuestionFromSurvey(@RequestBody QuestionDto questionDto, @PathVariable Long id){
        this.questionService.deleteQuestionFromSurveyById(id,questionDto);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(Urls.Survey.question.id.FULL)
    public ResponseEntity<String> updateQuestionInSurvey(@RequestBody QuestionDto questionDto, @PathVariable Long id){
        this.questionService.updateQuestion(id,questionDto);
        return new ResponseEntity<>("Question updated successfully",HttpStatus.OK);
    }

    @PostMapping(Urls.Survey.pass.id.FULL)
    public ResponseEntity<String> passSurvey(@RequestBody SurveyPassDto surveyPassDto, @PathVariable Long id){
        this.surveyService.passSurveyById(id, surveyPassDto);
        return new ResponseEntity<>("Survey passed successfully", HttpStatus.OK);
    }

    @GetMapping(Urls.Survey.details.id.FULL)
    public ResponseEntity<List<DetalizationDto>> getPassedSurveysByUserId(@PathVariable Long id){
        List<Answer> answer = answerService.getAllByUserId(id);
        if (answer == null){
            throw new ResourceNotFoundException("Surveys of user with id " + id + "not found", "user id");
        }
        List<DetalizationDto> result = this.surveyService.getUserDetalization(answer);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
