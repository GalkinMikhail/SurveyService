package com.example.fabriquetesttask.mapper;

import com.example.fabriquetesttask.dto.SurveyDto;
import com.example.fabriquetesttask.model.Survey;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    SurveyMapper SURVEY_MAPPER = Mappers.getMapper(SurveyMapper.class);

    @Named("surveyToSurveyDto")
    SurveyDto surveyToSurveyDto(Survey entity);
    Survey surveyDtoToSurvey(SurveyDto dto);
    @IterableMapping(qualifiedByName = "surveyToSurveyDto")
    List<SurveyDto> allToDto(List<Survey> surveys);
}
