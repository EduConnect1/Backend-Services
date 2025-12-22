package com.example.demo.OnlineExam.service;

import com.example.demo.OnlineExam.DTO.ExamResultResponse;
import com.example.demo.OnlineExam.DTO.StartExamRequest;
import com.example.demo.OnlineExam.DTO.SubmitAnswerRequest;

public interface ExamAttemptService {

    Long startExam(StartExamRequest request);

    void submitAnswer(SubmitAnswerRequest request);

    ExamResultResponse submitExam(Long attemptId);
}
