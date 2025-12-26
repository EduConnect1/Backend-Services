package com.example.demo.timetable.service;

import com.example.demo.timetable.dto.TimetableSessionRequest;
import com.example.demo.timetable.dto.TimetableSessionResponse;

import java.util.List;

public interface TimetableService {

    TimetableSessionResponse createSession(TimetableSessionRequest request);

    TimetableSessionResponse updateSession(Long sessionId, TimetableSessionRequest request);

    void deleteSession(Long sessionId);

    List<TimetableSessionResponse> getClassTimetable(Long classId);

    List<TimetableSessionResponse> getTeacherTimetable(Long teacherId);
}
