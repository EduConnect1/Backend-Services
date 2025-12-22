package com.example.demo.Timetable.Service;

import com.example.demo.Timetable.DTO.TimetableSessionRequest;
import com.example.demo.Timetable.DTO.TimetableSessionResponse;

import java.util.List;

public interface TimetableService {

    TimetableSessionResponse createSession(TimetableSessionRequest request);

    TimetableSessionResponse updateSession(Long sessionId, TimetableSessionRequest request);

    void deleteSession(Long sessionId);

    List<TimetableSessionResponse> getClassTimetable(Long classId);

    List<TimetableSessionResponse> getTeacherTimetable(Long teacherId);
}
