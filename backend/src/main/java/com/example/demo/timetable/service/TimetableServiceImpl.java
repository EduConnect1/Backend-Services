package com.example.demo.timetable.service;

import com.example.demo.timetable.dto.TimetableSessionRequest;
import com.example.demo.timetable.dto.TimetableSessionResponse;
import com.example.demo.timetable.model.TimetableSession;
import com.example.demo.timetable.repository.TimetableSessionRepository;

import com.example.demo.schoolstructure.model.SchoolClass;
import com.example.demo.schoolstructure.model.Teacher;
import com.example.demo.schoolstructure.model.Subject;
import com.example.demo.schoolstructure.repository.SchoolClassRepository;
import com.example.demo.schoolstructure.repository.TeacherRepository;
import com.example.demo.schoolstructure.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableSessionRepository sessionRepository;
    private final SchoolClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public TimetableSessionResponse createSession(TimetableSessionRequest request) {

        SchoolClass schoolClass = classRepository.findById(request.classId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        TimetableSession session = TimetableSession.builder()
                .schoolClass(schoolClass)
                .teacher(teacher)
                .subject(subject)
                .dayOfWeek(request.dayOfWeek())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();

        sessionRepository.save(session);

        return mapToResponse(session);
    }

    @Override
    public TimetableSessionResponse updateSession(Long sessionId, TimetableSessionRequest request) {
        TimetableSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setSchoolClass(classRepository.findById(request.classId())
                .orElseThrow(() -> new RuntimeException("Class not found")));
        session.setTeacher(teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found")));
        session.setSubject(subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found")));
        session.setDayOfWeek(request.dayOfWeek());
        session.setStartTime(request.startTime());
        session.setEndTime(request.endTime());

        sessionRepository.save(session);

        return mapToResponse(session);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public List<TimetableSessionResponse> getClassTimetable(Long classId) {
        SchoolClass schoolClass = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        return sessionRepository.findBySchoolClass(schoolClass).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableSessionResponse> getTeacherTimetable(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return sessionRepository.findByTeacher(teacher).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TimetableSessionResponse mapToResponse(TimetableSession session) {
        return new TimetableSessionResponse(
                session.getId(),
                session.getSchoolClass().getId(),
                session.getTeacher().getId(),
                session.getSubject().getId(),
                session.getDayOfWeek(),
                session.getStartTime(),
                session.getEndTime()
        );
    }
}

