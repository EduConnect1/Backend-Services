package com.example.demo.OnlineExam.service;

import com.example.demo.OnlineExam.repository.*;


import com.example.demo.OnlineExam.DTO.*;

import com.example.demo.OnlineExam.Model.*;

import com.example.demo.OnlineExam.service.QuestionServiceImpl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final OptionRepository optionRepository;

    @Override
    public QuestionResponse createQuestion(CreateQuestionRequest request) {

        Exam exam = examRepository.findById(request.examId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Question question = Question.builder()
                .exam(exam)
                .questionText(request.questionText())
                .type(request.type())
                .marks(request.marks())
                .build();

        questionRepository.save(question);

        return new QuestionResponse(
                question.getId(),
                question.getQuestionText(),
                question.getType(),
                question.getMarks(),
                List.of()
        );
    }

    @Override
    public List<QuestionResponse> getQuestionsByExam(Long examId) {
        return questionRepository.findByExamId(examId)
                .stream()
                .map(q -> new QuestionResponse(
                        q.getId(),
                        q.getQuestionText(),
                        q.getType(),
                        q.getMarks(),
                        optionRepository.findByQuestionId(q.getId())
                                .stream()
                                .map(o -> new OptionResponse(o.getId(), o.getOptionText()))
                                .toList()
                ))
                .toList();
    }
}
