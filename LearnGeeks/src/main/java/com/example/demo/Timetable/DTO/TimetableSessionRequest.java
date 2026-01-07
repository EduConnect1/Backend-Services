package com.example.demo.timetable.dto;

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
