package com.example.fabriquetesttask.service.implement;

import com.example.fabriquetesttask.dto.QuestionDto;
import com.example.fabriquetesttask.exception.ResourceNotFoundException;
import com.example.fabriquetesttask.mapper.QuestionMapper;
import com.example.fabriquetesttask.model.Enums.QuestionType;
import com.example.fabriquetesttask.model.Question;
import com.example.fabriquetesttask.model.Survey;
import com.example.fabriquetesttask.repository.QuestionRepository;
import com.example.fabriquetesttask.repository.SurveyRepository;
import com.example.fabriquetesttask.service.interfaces.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;


    @Override
    public void addQuestionToSurveyById(Long id, QuestionDto questionDto) {
        Survey survey = surveyRepository.getById(id);
        QuestionType questionType = findType(questionDto.getType());
        List<Question> questionList = survey.getQuestionList();
        Question question = new Question();
        question.setText(questionDto.getText());
        question.setType(questionType);
        questionList.add(question);
        survey.setQuestionList(questionList);
        questionRepository.save(question);
        surveyRepository.save(survey);
    }

    @Override
    public void deleteQuestionFromSurveyById(Long id, QuestionDto questionDto) {
        QuestionType questionType = findType(questionDto.getType());
        Question question = questionRepository.findByTextAndType(questionDto.getText(), questionType);
        if (question == null){
            throw new ResourceNotFoundException("Question not found", "question text/type");
        }
        Survey survey = surveyRepository.getById(id);
        if (survey == null){
            throw new ResourceNotFoundException("Survey with id " + id + " not found", "survey id");
        }
        List<Question> questionList = survey.getQuestionList();
        questionList.removeIf(n -> n.equals(question));
        survey.setQuestionList(questionList);
        surveyRepository.save(survey);
        questionRepository.delete(question);
    }

    @Override
    public void updateQuestion(Long id, QuestionDto questionDto) {
        Question question = questionRepository.getById(id);
        QuestionType questionType = findType(questionDto.getType());
        if (question == null){
            throw new ResourceNotFoundException("Question not found", "question text/type");
        }
        question.setText(questionDto.getText());
        question.setType(questionType);
        questionRepository.save(question);
    }

    @Override
    public QuestionDto getQuestionById(Long id) {
        return questionToQuestionDto(questionRepository.getById(id));
    }

    private QuestionDto questionToQuestionDto(Question question){
        return QuestionMapper.QUESTION_MAPPER.questionToQuestionDto(question);
    }
    private QuestionType findType(String type){
        if (QuestionType.TEXT.toString().equals(type)){
            return QuestionType.TEXT;
        }
        if (QuestionType.SINGLE_SELECT.toString().equals(type)){
            return QuestionType.SINGLE_SELECT;
        }
        else
            return QuestionType.MULTI_SELECT;
    }
}
