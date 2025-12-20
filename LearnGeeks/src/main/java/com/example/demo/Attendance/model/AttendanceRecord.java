package com.example.demo.Attendance.model;
import com.example.demo.SchoolStructure.Model.Student;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "attendance_records")


public class AttendanceRecord {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "attendance_session_id")
    private AttendanceSession attendanceSession;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @Column(length = 255)
    private String remarks;
}

    

