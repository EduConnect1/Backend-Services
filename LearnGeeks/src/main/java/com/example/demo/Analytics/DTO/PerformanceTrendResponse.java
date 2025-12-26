package com.example.demo.analytics.dto;

import java.time.LocalDate;

public record PerformanceTrendResponse(

        LocalDate date,
        double averageScore
) {}
