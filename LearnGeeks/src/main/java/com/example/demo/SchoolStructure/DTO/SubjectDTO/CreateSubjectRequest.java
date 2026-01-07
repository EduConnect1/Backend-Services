package com.example.demo.schoolstructure.dto.subjectdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSubjectRequest(
    @NotBlank(message = "Subject name is required")
    String name,

    @NotNull(message = "Class ID is required")
    Long classId
) {}
