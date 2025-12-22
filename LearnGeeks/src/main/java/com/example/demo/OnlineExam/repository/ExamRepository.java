package com.example.demo.OnlineExam.repository;

import com.example.demo.OnlineExam.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);

    List<Exam> findByPublishedTrue();
}

