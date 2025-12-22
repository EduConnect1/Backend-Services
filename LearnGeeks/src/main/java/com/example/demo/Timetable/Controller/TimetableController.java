package com.example.demo.Timetable.Controller;

import com.example.demo.Timetable.DTO.TimetableSessionRequest;
import com.example.demo.Timetable.DTO.TimetableSessionResponse;
import com.example.demo.Timetable.Service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetables")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping
    public ResponseEntity<TimetableSessionResponse> createSession(@RequestBody TimetableSessionRequest request) {
        return ResponseEntity.ok(timetableService.createSession(request));
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<TimetableSessionResponse> updateSession(
            @PathVariable Long sessionId,
            @RequestBody TimetableSessionRequest request) {
        return ResponseEntity.ok(timetableService.updateSession(sessionId, request));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long sessionId) {
        timetableService.deleteSession(sessionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<TimetableSessionResponse>> getClassTimetable(@PathVariable Long classId) {
        return ResponseEntity.ok(timetableService.getClassTimetable(classId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TimetableSessionResponse>> getTeacherTimetable(@PathVariable Long teacherId) {
        return ResponseEntity.ok(timetableService.getTeacherTimetable(teacherId));
    }
}

