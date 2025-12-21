package com.example.demo.Assignment.repository;
import com.example.demo.Assignment.Model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findBySubjectId(Long subjectId);

    List<Assignment> findByTeacherId(Long teacherId);
}
