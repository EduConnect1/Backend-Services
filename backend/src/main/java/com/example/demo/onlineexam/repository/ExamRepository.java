package com.example.demo.onlineexam.repository;

import com.example.demo.onlineexam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByCourseId(Long courseId);

    List<Exam> findByPublishedTrue();
}

