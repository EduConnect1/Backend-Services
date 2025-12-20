package com.example.demo.Attendance.repository;
import com.example.demo.Attendance.model.AttendanceRecord;
import com.example.demo.Attendance.model.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    Optional<AttendanceRecord> findByAttendanceSessionIdAndStudentId(
            Long sessionId,
            Long studentId
    );

    List<AttendanceRecord> findByStudentId(Long studentId);

    List<AttendanceRecord> findByAttendanceSessionAttendanceDate(LocalDate date);

    List<AttendanceRecord> findByStudentIdAndAttendanceSessionAttendanceDateBetween(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate
    );

    long countByStudentIdAndStatus(Long studentId, AttendanceStatus status);
}
