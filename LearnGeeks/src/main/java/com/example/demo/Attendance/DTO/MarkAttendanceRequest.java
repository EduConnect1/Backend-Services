package com.example.demo.Attendance.DTO;
import com.example.demo.Attendance.model.AttendanceStatus;
import jakarta.validation.constraints.NotNull;


public class MarkAttendanceRequest {
    

        @NotNull(message = "Student ID is required")
        Long studentId;

        @NotNull(message = "Attendance status is required")
        AttendanceStatus status;

        
 {
}

    
}
