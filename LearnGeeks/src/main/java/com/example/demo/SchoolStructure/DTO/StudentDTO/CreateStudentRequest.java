package com.example.demo.schoolstructure.dto.studentdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStudentRequest(
    @NotNull(message = "User ID is required")
    Long userId,

    @NotBlank(message = "Admission number is required")
    String admissionNumber,

    @NotNull(message = "Class ID is required")
    Long classId
) {}
