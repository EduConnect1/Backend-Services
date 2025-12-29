package com.example.demo.Analytics.DTO;

import java.time.LocalDate;

public record AttendanceHeatMapResponse(

        LocalDate date,
        double attendancePercentage
) {}

