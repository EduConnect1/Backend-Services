package com.example.demo.LearningManagementSystem.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lesson_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contentUrl; // PDF/video link

    @ManyToOne(optional = false)
    @JoinColumn(name = "module_id")
    private Module module;
}
