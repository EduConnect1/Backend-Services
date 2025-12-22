package com.example.demo.ResultManagement.Model;
import com.example.demo.SchoolStructure.Model.SchoolClass;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass; 
}
