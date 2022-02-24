package com.example.fabriquetesttask.mapper;

import com.example.fabriquetesttask.dto.AnswerDto;
import com.example.fabriquetesttask.model.Answer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerMapper ANSWER_MAPPER = Mappers.getMapper(AnswerMapper.class);

    @Named("answerToAnswerDto")
    AnswerDto answerToAnswerDto(Answer entity);
    Answer answerDtoToAnswer(AnswerDto dto);
    @IterableMapping(qualifiedByName = "answerToAnswerDto")
    List<AnswerDto> allToDto(List<Answer> answers);
}
