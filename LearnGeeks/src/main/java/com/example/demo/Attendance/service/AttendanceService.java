package com.example.demo.attendance.service;

import com.example.demo.attendance.dto.*;
import java.time.LocalDate;
import java.util.List;
public interface AttendanceService {

    AttendanceSessionResponse createAttendanceSession(CreateAttendanceSessionRequest request);

    void markAttendance(Long sessionId, List<MarkAttendanceRequest> requests);

    List<AttendanceRecordResponse> getAttendanceBySession(Long sessionId);

    StudentAttendanceSummaryResponse getStudentAttendanceSummary(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate
    );

    ClassAttendanceSummaryResponse getClassAttendanceSummary(Long classId);
}
