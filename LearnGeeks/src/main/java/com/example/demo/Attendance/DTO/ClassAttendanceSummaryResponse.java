package com.example.demo.Attendance.DTO;

public record ClassAttendanceSummaryResponse(
    Long classId,
    String className,
    long totalStudents,
    double averageAttendancePercentage
) {}
