package com.example.demo.fees.model;

import com.example.demo.schoolstructure.model.Student;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_structure_id")
    private FeeStructure feeStructure;

    private Double amountDue;

    private Double amountPaid;

    private Boolean fullyPaid;
}
