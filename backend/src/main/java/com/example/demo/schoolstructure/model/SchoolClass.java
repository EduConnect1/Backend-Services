package com.example.demo.schoolstructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;              

    @Column(nullable = false)
    private String academicYear;     
}
