package com.example.demo.assignment.repository;
import com.example.demo.assignment.model.AssignmentSubmission;
import com.example.demo.assignment.model.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long> {

    Optional<AssignmentSubmission> findByAssignmentIdAndStudentId(
            Long assignmentId,
            Long studentId
    );

    List<AssignmentSubmission> findByAssignmentId(Long assignmentId);

    List<AssignmentSubmission> findByStudentId(Long studentId);
    List<AssignmentSubmission> findByAssignmentSubjectId(Long subjectId);

    long countByAssignmentIdAndStatus(Long assignmentId, AssignmentStatus status);
}
