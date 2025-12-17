package com.example.demo.SchoolStructure.Model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "school_classes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassModel {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;              // P1, P2, S1, S2

    @Column(nullable = false)
    private String academicYear;      // 2024-2025
}

    

