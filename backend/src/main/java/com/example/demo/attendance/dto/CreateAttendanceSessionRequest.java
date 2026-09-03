package com.example.demo.attendance.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateAttendanceSessionRequest(
    @NotNull(message = "Class ID is required")
    Long schoolClassId,

    @NotNull(message = "Subject ID is required")
    Long subjectId,

    @NotNull(message = "Teacher ID is required")
    Long teacherId,

    @NotNull(message = "Attendance date is required")
    LocalDate attendanceDate
) {}
