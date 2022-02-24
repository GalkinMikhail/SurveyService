package com.example.fabriquetesttask.mapper;

import com.example.fabriquetesttask.dto.QuestionDto;
import com.example.fabriquetesttask.model.Question;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    @Named("questionToQuestionDto")
    QuestionDto questionToQuestionDto(Question entity);
    Question questionDtoToQuestion(QuestionDto dto);
    @IterableMapping(qualifiedByName = "questionToQuestionDto")
    List<QuestionDto> allToDto(List<Question> questions);
}
