package com.example.fabriquetesttask.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveyCreationDto {
    private String title;
    private LocalDateTime expirationDate;
    private String description;
}
