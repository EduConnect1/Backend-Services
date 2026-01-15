package com.example.demo.attendance.dto;

import com.example.demo.attendance.model.AttendanceStatus;

public record AttendanceRecordResponse(
    Long studentId,
    String studentName,
    AttendanceStatus status,
    String remarks
) {}
