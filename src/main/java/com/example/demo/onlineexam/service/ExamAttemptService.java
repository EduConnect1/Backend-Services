package com.example.demo.onlineexam.service;

import com.example.demo.onlineexam.dto.ExamResultResponse;
import com.example.demo.onlineexam.dto.StartExamRequest;
import com.example.demo.onlineexam.dto.SubmitAnswerRequest;

public interface ExamAttemptService {

    Long startExam(StartExamRequest request);

    void submitAnswer(SubmitAnswerRequest request);

    ExamResultResponse submitExam(Long attemptId);
}
