package com.example.demo.SchoolStructure.DTO.ParentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentResponse {

    private Long id;
    private Long userId;
    private Long studentId;
    private String studentAdmissionNumber;
}
