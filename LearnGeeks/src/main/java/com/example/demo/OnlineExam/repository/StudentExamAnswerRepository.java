package com.example.demo.OnlineExam.repository;

import com.example.demo.OnlineExam.Model.StudentExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExamAnswerRepository
        extends JpaRepository<StudentExamAnswer, Long> {

    List<StudentExamAnswer> findByAttemptId(Long attemptId);
}
