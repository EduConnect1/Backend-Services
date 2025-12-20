package com.example.demo.SchoolStructure.DTO.ParentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data


public class CreateParentRequest {
   

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Student ID is required")
    private Long studentId;
}

    

