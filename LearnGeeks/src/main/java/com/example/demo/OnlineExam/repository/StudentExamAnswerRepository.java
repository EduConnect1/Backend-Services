package com.example.demo.onlineexam.repository;

import com.example.demo.onlineexam.model.StudentExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExamAnswerRepository
        extends JpaRepository<StudentExamAnswer, Long> {

    List<StudentExamAnswer> findByAttemptId(Long attemptId);
}
