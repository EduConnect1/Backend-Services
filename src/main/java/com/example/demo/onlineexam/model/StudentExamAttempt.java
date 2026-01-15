package com.example.demo.onlineexam.model;

import com.example.demo.schoolstructure.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_exam_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exam exam;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int score;

    private boolean submitted;
}
