package com.example.demo.Assignment.service;

import com.example.demo.Assignment.DTO.AssignmentResponse;
import com.example.demo.Assignment.DTO.CreateAssignmentRequest;
import com.example.demo.Assignment.DTO.SubmitAssignmentRequest;
import com.example.demo.Assignment.DTO.GradeAssignmentRequest;
import com.example.demo.Assignment.DTO.AssignmentSubmissionResponse;
import com.example.demo.Assignment.DTO.StudentAssignmentSummaryResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment(CreateAssignmentRequest request);

    List<AssignmentResponse> getAssignmentsBySubject(Long subjectId);

    void submitAssignment(Long studentId, SubmitAssignmentRequest request);

    void gradeAssignment(Long submissionId, GradeAssignmentRequest request);

    List<AssignmentSubmissionResponse> getSubmissionsByAssignment(Long assignmentId);

    StudentAssignmentSummaryResponse getStudentAssignmentSummary(Long studentId);
}
