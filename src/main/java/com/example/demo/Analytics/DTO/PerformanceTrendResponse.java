package com.example.demo.Analytics.DTO;

import java.time.LocalDate;

public record PerformanceTrendResponse(

        LocalDate date,
        double averageScore
) {}
