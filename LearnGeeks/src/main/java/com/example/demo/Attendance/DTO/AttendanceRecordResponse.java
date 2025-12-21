package com.example.demo.Attendance.DTO;

import com.example.demo.Attendance.model.AttendanceStatus;

public record AttendanceRecordResponse(
    Long studentId,
    String studentName,
    AttendanceStatus status,
    String remarks
) {}
