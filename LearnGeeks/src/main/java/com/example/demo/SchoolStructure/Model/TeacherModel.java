package com.example.demo.SchoolStructure.Model;
import jakarta.persistence.*;
    import lombok.*;
    
    import java.util.Set;
    
    @Entity
    @Table(name = "teachers")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

public class TeacherModel {

    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user;
    
        @Column(unique = true, nullable = false)
        private String employeeNumber;
    
        @ManyToMany
        @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
        )
        private Set<SubjectModel> subjects;
    }
    
    



