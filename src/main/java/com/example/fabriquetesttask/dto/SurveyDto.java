package com.example.fabriquetesttask.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.example.fabriquetesttask.model.Question;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyDto {
    private String title;
    private LocalDateTime expirationDate;
    private String description;
    private List<Question> questionList;
}
