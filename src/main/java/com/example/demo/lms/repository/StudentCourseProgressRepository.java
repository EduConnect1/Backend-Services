package com.example.demo.lms.repository;

import com.example.demo.lms.model.StudentCourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface StudentCourseProgressRepository extends JpaRepository<StudentCourseProgress, Long> {

    Optional<StudentCourseProgress> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<StudentCourseProgress> findByStudentId(Long studentId);

    List<StudentCourseProgress> findByCourseId(Long courseId);
}
