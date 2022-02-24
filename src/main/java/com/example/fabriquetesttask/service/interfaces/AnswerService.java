package com.example.fabriquetesttask.service.interfaces;

import com.example.fabriquetesttask.dto.AnswerDto;
import com.example.fabriquetesttask.model.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAllByUserId(Long id);
    void deleteAnswerById(Long id);

}
