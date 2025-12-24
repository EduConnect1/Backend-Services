package com.example.demo.Timetable.DTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record TimetableSessionRequest(
        Long classId,
        Long teacherId,
        Long subjectId,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {}
