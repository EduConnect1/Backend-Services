package com.example.demo.onlineexam.service;

import com.example.demo.onlineexam.dto.ExamResultResponse;
import com.example.demo.onlineexam.dto.StartExamRequest;
import com.example.demo.onlineexam.dto.SubmitAnswerRequest;

import com.example.demo.onlineexam.model.*;
import com.example.demo.onlineexam.repository.*;
import com.example.demo.schoolstructure.repository.StudentRepository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamAttemptServiceImpl implements ExamAttemptService {

    private final StudentExamAttemptRepository attemptRepository;
    private final StudentExamAnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @Override
public Long startExam(StartExamRequest request) {

    studentRepository.findById(request.studentId())
            .orElseThrow(() -> new RuntimeException("Student not found"));

    examRepository.findById(request.examId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));

    
    attemptRepository.findByStudentIdAndExamId(
            request.studentId(),
            request.examId()
    ).ifPresent(a -> {
        throw new RuntimeException("You have already attempted this exam");
    });

    StudentExamAttempt attempt = StudentExamAttempt.builder()
            .student(
                studentRepository.getReferenceById(request.studentId())
            )
            .exam(
                examRepository.getReferenceById(request.examId())
            )
            .startTime(LocalDateTime.now())
            .submitted(false)
            .score(0)
            .build();

    return attemptRepository.save(attempt).getId();
}


    @Override
    public void submitAnswer(SubmitAnswerRequest request) {

        StudentExamAttempt attempt = attemptRepository.findById(request.attemptId())
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        Question question = questionRepository.findById(request.questionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        StudentExamAnswer answer = StudentExamAnswer.builder()
                .attempt(attempt)
                .question(question)
                .answer(request.answer())
                .build();

        answerRepository.save(answer);
    }

    @Override
    public ExamResultResponse submitExam(Long attemptId) {

        StudentExamAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        int score = 0;

        for (StudentExamAnswer ans : answerRepository.findByAttemptId(attemptId)) {

            Question q = ans.getQuestion();

            if (q.getType() == QuestionType.MCQ ||
                q.getType() == QuestionType.TRUE_FALSE) {

                boolean correct = optionRepository
                        .findByQuestionId(q.getId())
                        .stream()
                        .anyMatch(o -> o.isCorrect()
                                && o.getOptionText()
                                .equalsIgnoreCase(ans.getAnswer()));

                if (correct) {
                    score += q.getMarks();
                }
            }
        }

        attempt.setScore(score);
        attempt.setSubmitted(true);
        attempt.setEndTime(LocalDateTime.now());
        attemptRepository.save(attempt);

        return new ExamResultResponse(
                attempt.getStudent().getId(),
                attempt.getExam().getId(),
                score,
                score >= (attempt.getExam().getTotalMarks() * 0.5)
        );
    }
}
