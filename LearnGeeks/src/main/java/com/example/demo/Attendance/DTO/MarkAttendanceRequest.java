package com.example.demo.attendance.dto;

import com.example.demo.attendance.model.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

public record MarkAttendanceRequest(
    @NotNull(message = "Student ID is required")
    Long studentId,

    @NotNull(message = "Attendance status is required")
    AttendanceStatus status,
    
    String remarks
) {}
