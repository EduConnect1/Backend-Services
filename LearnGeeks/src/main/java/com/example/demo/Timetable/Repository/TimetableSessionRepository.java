package com.example.demo.Timetable.Repository;



import com.example.demo.Timetable.Model.TimetableSession;
import com.example.demo.SchoolStructure.Model.SchoolClass;
import com.example.demo.SchoolStructure.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface TimetableSessionRepository extends JpaRepository<TimetableSession, Long> {

    List<TimetableSession> findBySchoolClassAndDayOfWeek(SchoolClass schoolClass, DayOfWeek dayOfWeek);

    List<TimetableSession> findByTeacherAndDayOfWeek(Teacher teacher, DayOfWeek dayOfWeek);

    List<TimetableSession> findBySchoolClass(SchoolClass schoolClass);

    List<TimetableSession> findByTeacher(Teacher teacher);
}

