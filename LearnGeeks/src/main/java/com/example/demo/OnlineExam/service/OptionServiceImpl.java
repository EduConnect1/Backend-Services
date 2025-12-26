package com.example.demo.onlineexam.service;

import com.example.demo.onlineexam.dto.CreateOptionRequest;
import com.example.demo.onlineexam.model.Option;
import com.example.demo.onlineexam.model.Question;
import com.example.demo.onlineexam.repository.OptionRepository;
import com.example.demo.onlineexam.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void createOption(CreateOptionRequest request) {

        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Option option = Option.builder()
                .question(question)
                .optionText(request.optionText())
                .correct(request.correct())
                .build();

        optionRepository.save(option);
    }
}
