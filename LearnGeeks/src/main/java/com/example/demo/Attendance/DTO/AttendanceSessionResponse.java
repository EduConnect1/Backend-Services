package com.example.demo.Attendance.DTO;
import java.time.LocalDate;

import lombok.Data;
@Data

public class AttendanceSessionResponse {
   

        Long id;
        Long schoolClassId;
        String className;
        Long subjectId;
        String subjectName;
        Long teacherId;
        String teacherName;
        LocalDate attendanceDate;
}
