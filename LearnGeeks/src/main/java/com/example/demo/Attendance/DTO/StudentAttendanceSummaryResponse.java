package com.example.demo.Attendance.DTO;

public record StudentAttendanceSummaryResponse(
    Long studentId,
    String studentName,
    long presentCount,
    long absentCount,
    long lateCount,
    double attendancePercentage
) {}
