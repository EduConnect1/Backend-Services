package com.example.demo.learningmanagementsystem.model;

import com.example.demo.schoolstructure.model.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "student_course_progress",
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private int completedLessons;

    @Column(nullable = false)
    private double completionPercentage;
}
