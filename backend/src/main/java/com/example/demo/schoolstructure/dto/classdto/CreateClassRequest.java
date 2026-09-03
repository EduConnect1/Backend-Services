package com.example.demo.schoolstructure.dto.classdto;

import jakarta.validation.constraints.NotBlank;

public record CreateClassRequest(
    @NotBlank(message = "Class name is required")
    String name,           

    @NotBlank(message = "Academic year is required")
    String academicYear   
) {}
