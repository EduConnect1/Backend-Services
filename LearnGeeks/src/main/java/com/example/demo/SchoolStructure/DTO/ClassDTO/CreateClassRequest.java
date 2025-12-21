package com.example.demo.SchoolStructure.DTO.ClassDTO;

import jakarta.validation.constraints.NotBlank;

public record CreateClassRequest(
    @NotBlank(message = "Class name is required")
    String name,           // P1, S2

    @NotBlank(message = "Academic year is required")
    String academicYear   // 2024-2025
) {}
