package com.example.demo.onlineexam.repository;

import com.example.demo.onlineexam.model.StudentExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentExamAttemptRepository
        extends JpaRepository<StudentExamAttempt, Long> {

    Optional<StudentExamAttempt> findByStudentIdAndExamId(
            Long studentId,
            Long examId
    );
}
