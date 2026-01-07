package com.example.demo.onlineexam.controller;

import com.example.demo.onlineexam.dto.*;
import com.example.demo.onlineexam.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    
    private final OptionService optionService;
    private final ExamAttemptService examAttemptService;

    @PostMapping
    public ResponseEntity<ExamResponse> createExam(
            @RequestBody CreateExamRequest request) {

        ExamResponse response = examService.createExam(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/questions/options")
    public ResponseEntity<Void> addOption(
            @RequestBody CreateOptionRequest request) {

        optionService.createOption(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start")
    public ResponseEntity<Long> startExam(
            @RequestBody StartExamRequest request) {

        Long attemptId = examAttemptService.startExam(request);
        return ResponseEntity.ok(attemptId);
    }

    @PostMapping("/answer")
    public ResponseEntity<Void> submitAnswer(
            @RequestBody SubmitAnswerRequest request) {

        examAttemptService.submitAnswer(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/submit/{attemptId}")
    public ResponseEntity<ExamResultResponse> submitExam(
            @PathVariable Long attemptId) {

        ExamResultResponse result =
                examAttemptService.submitExam(attemptId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamResponse> getExam(
            @PathVariable Long examId) {

        return ResponseEntity.ok(
                examService.getExamById(examId));
    }
}
