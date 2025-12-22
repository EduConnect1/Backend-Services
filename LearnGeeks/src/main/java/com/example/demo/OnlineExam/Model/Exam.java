package com.example.demo.OnlineExam.Model;



import com.example.demo.LearningManagementSystem.Model.Course;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private int durationMinutes;

    private int totalMarks;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean published;
}
