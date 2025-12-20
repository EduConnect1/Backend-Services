package com.example.demo.Attendance.DTO;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public class CreateAttendanceSessionRequest {
    

        @NotNull(message = "Class ID is required")
        Long schoolClassId;

        @NotNull(message = "Subject ID is required")
        Long subjectId;

        @NotNull(message = "Teacher ID is required")
        Long teacherId;


        @NotNull(message = "Attendance date is required")
        LocalDate attendanceDate ;
        
    
}
