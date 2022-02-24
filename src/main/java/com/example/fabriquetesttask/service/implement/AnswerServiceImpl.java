package com.example.fabriquetesttask.service.implement;

import com.example.fabriquetesttask.dto.AnswerDto;
import com.example.fabriquetesttask.mapper.AnswerMapper;
import com.example.fabriquetesttask.model.Answer;
import com.example.fabriquetesttask.repository.AnswerRepository;
import com.example.fabriquetesttask.service.interfaces.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllByUserId(Long id) {
        return answerRepository.finAllByUserId(id);
    }

    @Override
    public void deleteAnswerById(Long id) {
        answerRepository.deleteById(id);
    }

    private List<AnswerDto> allToDto(List<Answer> answers){
        return AnswerMapper.ANSWER_MAPPER.allToDto(answers);
    }
}
