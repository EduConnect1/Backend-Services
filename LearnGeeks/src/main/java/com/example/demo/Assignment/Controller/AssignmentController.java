package com.example.demo.assignment.controller;

import com.example.demo.assignment.dto.*;
import com.example.demo.assignment.service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    
    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(
            @Valid @RequestBody CreateAssignmentRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assignmentService.createAssignment(request));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsBySubject(
            @PathVariable Long subjectId
    ) {
        return ResponseEntity.ok(
                assignmentService.getAssignmentsBySubject(subjectId)
        );
    }

    @PostMapping("/submit/{studentId}")
    public ResponseEntity<Void> submitAssignment(
            @PathVariable Long studentId,
            @Valid @RequestBody SubmitAssignmentRequest request
    ) {
        assignmentService.submitAssignment(studentId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/grade/{submissionId}")
    public ResponseEntity<Void> gradeAssignment(
            @PathVariable Long submissionId,
            @Valid @RequestBody GradeAssignmentRequest request
    ) {
        assignmentService.gradeAssignment(submissionId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{assignmentId}/submissions")
    public ResponseEntity<List<AssignmentSubmissionResponse>> getSubmissions(
            @PathVariable Long assignmentId
    ) {
        return ResponseEntity.ok(
                assignmentService.getSubmissionsByAssignment(assignmentId)
        );
    }

    @GetMapping("/students/{studentId}/summary")
    public ResponseEntity<StudentAssignmentSummaryResponse> getStudentSummary(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                assignmentService.getStudentAssignmentSummary(studentId)
        );
    }
}
