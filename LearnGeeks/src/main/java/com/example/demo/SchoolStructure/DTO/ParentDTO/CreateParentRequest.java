package com.example.demo.SchoolStructure.DTO.ParentDTO;

import jakarta.validation.constraints.NotNull;

public record CreateParentRequest(
    @NotNull(message = "User ID is required")
    Long userId,

    @NotNull(message = "Student ID is required")
    Long studentId
) {}
