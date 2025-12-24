package com.example.demo.ResultManagement.repository;

import com.example.demo.ResultManagement.Model.ExamResult;
import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.ResultManagement.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    
    List<ExamResult> findByStudent(Student student);
    
    List<ExamResult> findBySubject(Subject subject);
}

