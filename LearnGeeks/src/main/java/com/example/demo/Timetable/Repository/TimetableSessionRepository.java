package com.example.demo.timetable.repository;

import com.example.demo.timetable.model.TimetableSession;
import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface TimetableSessionRepository extends JpaRepository<TimetableSession, Long> {

    List<TimetableSession> findBySchoolClassAndDayOfWeek(SchoolClass schoolClass, DayOfWeek dayOfWeek);

    List<TimetableSession> findByTeacherAndDayOfWeek(Teacher teacher, DayOfWeek dayOfWeek);

    List<TimetableSession> findBySchoolClass(SchoolClass schoolClass);

    List<TimetableSession> findByTeacher(Teacher teacher);
}

