package com.example.demo.OnlineExam.service;

import com.example.demo.OnlineExam.DTO.ExamResultResponse;
import com.example.demo.OnlineExam.DTO.StartExamRequest;
import com.example.demo.OnlineExam.DTO.SubmitAnswerRequest;

import com.example.demo.OnlineExam.Model.*;
import com.example.demo.OnlineExam.repository.*;
import com.example.demo.SchoolStructure.repository.StudentRepository;
import com.example.demo.SchoolStructure.Model.Student;

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

        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Exam exam = examRepository.findById(request.examId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        StudentExamAttempt attempt = StudentExamAttempt.builder()
                .student(student)
                .exam(exam)
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
