package com.example.demo.ResultManagement.Model;
import com.example.demo.SchoolStructure.Model.Student;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "report_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String term;

    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_card_id")
    private List<ExamResult> examResults;
}
