package com.example.demo.assignment.service;

import com.example.demo.assignment.dto.AssignmentResponse;
import com.example.demo.assignment.dto.CreateAssignmentRequest;
import com.example.demo.assignment.dto.SubmitAssignmentRequest;
import com.example.demo.assignment.dto.GradeAssignmentRequest;
import com.example.demo.assignment.dto.AssignmentSubmissionResponse;
import com.example.demo.assignment.dto.StudentAssignmentSummaryResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment(CreateAssignmentRequest request);

    List<AssignmentResponse> getAssignmentsBySubject(Long subjectId);

    void submitAssignment(Long studentId, SubmitAssignmentRequest request);

    void gradeAssignment(Long submissionId, GradeAssignmentRequest request);

    List<AssignmentSubmissionResponse> getSubmissionsByAssignment(Long assignmentId);

    StudentAssignmentSummaryResponse getStudentAssignmentSummary(Long studentId);
}
