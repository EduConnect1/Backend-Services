package com.example.demo.fees.model;

import com.example.demo.schoolstructure.model.SchoolClass;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fee_structures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    private String description;

    private Double amount;

    private int year;
}
