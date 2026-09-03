package com.example.demo.schoolstructure.dto.teacherdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTeacherRequest(
    @NotNull(message = "User ID is required")
    Long userId,

    @NotBlank(message = "Employee number is required")
    String employeeNumber
) {}
