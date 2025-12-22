package com.example.demo.OnlineExam.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_exam_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentExamAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudentExamAttempt attempt;

    @ManyToOne
    private Question question;

    private String answer;
}
