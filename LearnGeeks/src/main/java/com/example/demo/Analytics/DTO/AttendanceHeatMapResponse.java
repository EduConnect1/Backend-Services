package com.example.demo.analytics.dto;

import java.time.LocalDate;

public record AttendanceHeatMapResponse(

        LocalDate date,
        double attendancePercentage
) {}

