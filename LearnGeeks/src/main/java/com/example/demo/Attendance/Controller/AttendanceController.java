package com.example.demo.Attendance.Controller;
import com.example.demo.Attendance.DTO.*;
import com.example.demo.Attendance.service.AttendanceService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/sessions")
    public ResponseEntity<AttendanceSessionResponse> createSession(
            @Valid @RequestBody CreateAttendanceSessionRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(attendanceService.createAttendanceSession(request));
    }

    @PostMapping("/sessions/{sessionId}/mark")
    public ResponseEntity<Void> markAttendance(
            @PathVariable Long sessionId,
            @Valid @RequestBody List<MarkAttendanceRequest> requests
    ) {
        attendanceService.markAttendance(sessionId, requests);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<List<AttendanceRecordResponse>> getAttendanceBySession(
            @PathVariable Long sessionId
    ) {
        return ResponseEntity.ok(
                attendanceService.getAttendanceBySession(sessionId)
        );
    }

    @GetMapping("/students/{studentId}/summary")
    public ResponseEntity<StudentAttendanceSummaryResponse> getStudentSummary(
            @PathVariable Long studentId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return ResponseEntity.ok(
                attendanceService.getStudentAttendanceSummary(studentId, startDate, endDate)
        );
    }

    @GetMapping("/classes/{classId}/summary")
    public ResponseEntity<ClassAttendanceSummaryResponse> getClassSummary(
            @PathVariable Long classId
    ) {
        return ResponseEntity.ok(
                attendanceService.getClassAttendanceSummary(classId)
        );
    }
}
