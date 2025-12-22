package com.example.demo.OnlineExam.service;

import com.example.demo.OnlineExam.DTO.CreateQuestionRequest;
import com.example.demo.OnlineExam.DTO.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse createQuestion(CreateQuestionRequest request);

    List<QuestionResponse> getQuestionsByExam(Long examId);
}
