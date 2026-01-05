package com.example.demo.attendance.model;
import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Subject;
import com.example.demo.schoolstructure.model.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "attendance_sessions")
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;
}

    

