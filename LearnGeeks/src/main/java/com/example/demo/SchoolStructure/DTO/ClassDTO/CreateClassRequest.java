package com.example.demo.SchoolStructure.DTO.ClassDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClassRequest {

    @NotBlank(message = "Class name is required")
    private String name;           // P1, S2

    @NotBlank(message = "Academic year is required")
    private String academicYear;   // 2024-2025
}

    

