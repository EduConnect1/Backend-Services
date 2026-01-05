package com.example.demo.assignment.model;

import com.example.demo.schoolstructure.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a student's submission for a specific assignment.
 * Enforces unique constraint per student and assignment.
 */
@Entity
@Table(
    name = "assignment_submissions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"assignment_id", "student_id"}
    )
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA requirement
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"assignment", "student"}) // Prevent recursion in logs
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // Link to the assignment this submission belongs to
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    // The student who submitted the assignment
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // URL of the submitted file
    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    // Status of the submission: SUBMITTED, LATE, or GRADED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    // Marks assigned to the submission
    private Double marks;

    // Feedback provided by teacher
    @Column(length = 1000)
    private String feedback;

    // Date and time of submission
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}
