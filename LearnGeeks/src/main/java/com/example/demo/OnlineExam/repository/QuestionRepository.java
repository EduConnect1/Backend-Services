package com.example.demo.OnlineExam.repository;

import com.example.demo.OnlineExam.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByExamId(Long examId);
}