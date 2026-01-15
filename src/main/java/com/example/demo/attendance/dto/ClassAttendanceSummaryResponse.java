package com.example.demo.attendance.dto;

public record ClassAttendanceSummaryResponse(
    Long classId,
    String className,
    long totalStudents,
    double averageAttendancePercentage
) {}
