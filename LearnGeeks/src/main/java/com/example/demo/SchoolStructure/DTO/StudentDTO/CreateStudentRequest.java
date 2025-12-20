package com.example.demo.SchoolStructure.DTO.StudentDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data


public class CreateStudentRequest {
    

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Admission number is required")
    private String admissionNumber;

    @NotNull(message = "Class ID is required")
    private Long classId;
}

    

