package com.example.demo.timetable.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record TimetableSessionResponse(
        Long id,
        Long classId,
        Long teacherId,
        Long subjectId,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
