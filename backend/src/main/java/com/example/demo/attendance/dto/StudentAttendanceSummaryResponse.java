package com.example.demo.attendance.dto;

public record StudentAttendanceSummaryResponse(
    Long studentId,
    String studentName,
    long presentCount,
    long absentCount,
    long lateCount,
    double attendancePercentage
) {}
