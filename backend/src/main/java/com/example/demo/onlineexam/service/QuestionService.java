package com.example.demo.onlineexam.service;

import com.example.demo.onlineexam.dto.CreateQuestionRequest;
import com.example.demo.onlineexam.dto.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse createQuestion(CreateQuestionRequest request);

    List<QuestionResponse> getQuestionsByExam(Long examId);
}
