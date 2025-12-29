package com.example.demo.timetable.model;

import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Subject;
import com.example.demo.schoolstructure.model.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.time.DayOfWeek;

@Entity
@Table(name = "timetable_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimetableSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;
}
