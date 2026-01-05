package com.example.demo.assignment.service;

import com.example.demo.assignment.dto.AssignmentResponse;
import com.example.demo.assignment.dto.AssignmentSubmissionResponse;
import com.example.demo.assignment.dto.CreateAssignmentRequest;
import com.example.demo.assignment.dto.GradeAssignmentRequest;
import com.example.demo.assignment.dto.StudentAssignmentSummaryResponse;
import com.example.demo.assignment.dto.SubmitAssignmentRequest;

import com.example.demo.assignment.model.Assignment;
import com.example.demo.assignment.model.AssignmentSubmission;
import com.example.demo.assignment.model.AssignmentStatus;

import com.example.demo.assignment.repository.AssignmentSubmissionRepository;
import com.example.demo.assignment.repository.AssignmentRepository;

import com.example.demo.schoolstructure.model.Subject;
import com.example.demo.schoolstructure.repository.SubjectRepository;

import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.repository.TeacherRepository;

import com.example.demo.schoolstructure.model.Student;
import com.example.demo.schoolstructure.repository.StudentRepository;

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
                                saved.getCreatedAt());
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
                                                a.getCreatedAt()))
                                .toList();
        }

        @Override
        public void submitAssignment(Long studentId, SubmitAssignmentRequest request) {

                
                Assignment assignment = assignmentRepository.findById(request.assignmentId())
                                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

                
                submissionRepository.findByAssignmentIdAndStudentId(assignment.getId(), studentId)
                                .ifPresent(s -> {
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
                                                s.getSubmittedAt()))
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
                                submissionRate);
        }
}
