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
@NoArgsConstructor(access = AccessLevel.PROTECTED) 
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"assignment", "student"}) 
public class AssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentStatus status;

    private Double marks;

    @Column(length = 1000)
    private String feedback;

    
    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}
