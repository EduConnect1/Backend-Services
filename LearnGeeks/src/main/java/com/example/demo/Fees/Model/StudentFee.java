package com.example.demo.Fees.Model;



import com.example.demo.SchoolStructure.Model.Student;
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
