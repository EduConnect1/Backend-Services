package com.example.demo.schoolstructure.dto.parentdto;

import jakarta.validation.constraints.NotNull;

public record CreateParentRequest(
    @NotNull(message = "User ID is required")
    Long userId,

    @NotNull(message = "Student ID is required")
    Long studentId
) {}
