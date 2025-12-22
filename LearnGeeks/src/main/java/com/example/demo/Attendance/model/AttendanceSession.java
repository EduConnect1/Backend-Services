package com.example.demo.Attendance.model;
import com.example.demo.SchoolStructure.Model.SchoolClass;
import com.example.demo.SchoolStructure.Model.Subject;
import com.example.demo.SchoolStructure.Model.Teacher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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

    

