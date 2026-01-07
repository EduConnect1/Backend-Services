package com.example.demo.attendance.dto;

import java.time.LocalDate;

public record AttendanceSessionResponse(
    Long id,
    Long schoolClassId,
    String className,
    Long subjectId,
    String subjectName,
    Long teacherId,
    String teacherName,
    LocalDate attendanceDate
) {}
