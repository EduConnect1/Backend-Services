package com.example.demo.SchoolStructure.DTO.TeacherDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data

public class CreateTeacherRequest {
   

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Employee number is required")
    private String employeeNumber;
}

    

