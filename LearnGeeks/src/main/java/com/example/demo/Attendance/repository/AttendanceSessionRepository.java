package com.example.demo.Attendance.repository;
import com.example.demo.Attendance.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {

    Optional<AttendanceSession> findBySchoolClassIdAndSubjectIdAndAttendanceDate(
            Long schoolClassId,
            Long subjectId,
            LocalDate attendanceDate
    );

    List<AttendanceSession> findBySchoolClassId(Long schoolClassId);

    List<AttendanceSession> findByTeacherId(Long teacherId);
}

    

