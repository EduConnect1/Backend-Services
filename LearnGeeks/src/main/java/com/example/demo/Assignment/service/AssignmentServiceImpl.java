package com.example.demo.Assignment.service;

import com.example.demo.Assignment.DTO.AssignmentResponse;
import com.example.demo.Assignment.DTO.AssignmentSubmissionResponse;
import com.example.demo.Assignment.DTO.CreateAssignmentRequest;
import com.example.demo.Assignment.DTO.GradeAssignmentRequest;
import com.example.demo.Assignment.DTO.StudentAssignmentSummaryResponse;
import com.example.demo.Assignment.DTO.SubmitAssignmentRequest;

import com.example.demo.Assignment.Model.Assignment;
import com.example.demo.Assignment.Model.AssignmentSubmission;
import com.example.demo.Assignment.Model.AssignmentStatus;

import com.example.demo.Assignment.repository.AssignmentRepository;
import com.example.demo.Assignment.repository.AssignmentSubmissionRepository;

import com.example.demo.SchoolStructure.Model.Subject;
import com.example.demo.SchoolStructure.repository.SubjectRepository;

import com.example.demo.SchoolStructure.Model.Teacher;
import com.example.demo.SchoolStructure.repository.TeacherRepository;

import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.SchoolStructure.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentSubmissionRepository submissionRepository;

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public AssignmentResponse createAssignment(CreateAssignmentRequest request) {

        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        Assignment assignment = Assignment.builder()
                .title(request.title())
                .description(request.description())
                .subject(subject)
                .teacher(teacher)
                .deadline(request.deadline())
                .createdAt(LocalDateTime.now())
                .build();

        Assignment saved = assignmentRepository.save(assignment);

        return new AssignmentResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                subject.getId(),
                subject.getName(),
                teacher.getId(),
                teacher.getFullName(),
                saved.getDeadline(),
                saved.getCreatedAt()
        );
    }
    @Override
    public List<AssignmentResponse> getAssignmentsBySubject(Long subjectId) {

        return assignmentRepository.findBySubjectId(subjectId)
                .stream()
                .map(a -> new AssignmentResponse(
                        a.getId(),
                        a.getTitle(),
                        a.getDescription(),
                        a.getSubject().getId(),
                        a.getSubject().getName(),
                        a.getTeacher().getId(),
                        a.getTeacher().getFullName(),
                        a.getDeadline(),
                        a.getCreatedAt()
                ))
                .toList();
    }
    @Override
    public void submitAssignment(Long studentId, SubmitAssignmentRequest request) {

        Assignment assignment = assignmentRepository.findById(request.assignmentId())
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        submissionRepository.findByAssignmentIdAndStudentId(
                assignment.getId(),
                studentId
        ).ifPresent(s -> {
            throw new IllegalStateException("Assignment already submitted");
        });

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        boolean isLate = LocalDateTime.now().isAfter(assignment.getDeadline());

        AssignmentSubmission submission = AssignmentSubmission.builder()
                .assignment(assignment)
                .student(student)
                .fileUrl(request.fileUrl())
                .status(isLate ? AssignmentStatus.LATE : AssignmentStatus.SUBMITTED)
                .submittedAt(LocalDateTime.now())
                .build();

        submissionRepository.save(submission);
    }

    @Override
    public void gradeAssignment(Long submissionId, GradeAssignmentRequest request) {

        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Submission not found"));

        submission.setMarks(request.marks());
        submission.setFeedback(request.feedback());
        submission.setStatus(AssignmentStatus.GRADED);

        submissionRepository.save(submission);
    }

    @Override
    public List<AssignmentSubmissionResponse> getSubmissionsByAssignment(Long assignmentId) {

        return submissionRepository.findByAssignmentId(assignmentId)
                .stream()
                .map(s -> new AssignmentSubmissionResponse(
                        s.getStudent().getId(),
                        s.getStudent().getFullName(),
                        s.getFileUrl(),
                        s.getStatus(),
                        s.getMarks(),
                        s.getFeedback(),
                        s.getSubmittedAt()
                ))
                .toList();
    }
    @Override
    public StudentAssignmentSummaryResponse getStudentAssignmentSummary(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        long totalAssignments = assignmentRepository.count();
        long submitted = submissionRepository.findByStudentId(studentId).size();
        long late = submissionRepository.findByStudentId(studentId)
                .stream()
                .filter(s -> s.getStatus() == AssignmentStatus.LATE)
                .count();

        double submissionRate = totalAssignments == 0
                ? 0
                : (submitted * 100.0) / totalAssignments;

        return new StudentAssignmentSummaryResponse(
                student.getId(),
                student.getFullName(),
                totalAssignments,
                submitted,
                late,
                submissionRate
        );
    }
}
