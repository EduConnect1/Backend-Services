package com.example.demo.Assignment.Controller;



import com.example.demo.Assignment.DTO.*;
import com.example.demo.Assignment.service.AssignmentService;
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

    // 1️⃣ Create Assignment (Teacher/Admin)
    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(
            @Valid @RequestBody CreateAssignmentRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assignmentService.createAssignment(request));
    }

    // 2️⃣ Get Assignments by Subject (Teacher/Student)
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsBySubject(
            @PathVariable Long subjectId
    ) {
        return ResponseEntity.ok(
                assignmentService.getAssignmentsBySubject(subjectId)
        );
    }

    // 3️⃣ Submit Assignment (Student)
    @PostMapping("/submit/{studentId}")
    public ResponseEntity<Void> submitAssignment(
            @PathVariable Long studentId,
            @Valid @RequestBody SubmitAssignmentRequest request
    ) {
        assignmentService.submitAssignment(studentId, request);
        return ResponseEntity.ok().build();
    }

    // 4️⃣ Grade Assignment (Teacher)
    @PutMapping("/grade/{submissionId}")
    public ResponseEntity<Void> gradeAssignment(
            @PathVariable Long submissionId,
            @Valid @RequestBody GradeAssignmentRequest request
    ) {
        assignmentService.gradeAssignment(submissionId, request);
        return ResponseEntity.ok().build();
    }

    // 5️⃣ View Submissions for Assignment (Teacher/Admin)
    @GetMapping("/{assignmentId}/submissions")
    public ResponseEntity<List<AssignmentSubmissionResponse>> getSubmissions(
            @PathVariable Long assignmentId
    ) {
        return ResponseEntity.ok(
                assignmentService.getSubmissionsByAssignment(assignmentId)
        );
    }

    // 6️⃣ Student Assignment Analytics (Student/Parent/Admin)
    @GetMapping("/students/{studentId}/summary")
    public ResponseEntity<StudentAssignmentSummaryResponse> getStudentSummary(
            @PathVariable Long studentId
    ) {
        return ResponseEntity.ok(
                assignmentService.getStudentAssignmentSummary(studentId)
        );
    }
}
