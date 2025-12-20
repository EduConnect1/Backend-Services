package com.example.demo.Attendance.DTO;

import com.example.demo.Attendance.model.AttendanceStatus;
import lombok.Data;
@Data
public class AttendanceRecordResponse {

        Long studentId;
        String studentName;
        AttendanceStatus status;

        String remarks;
  
}
