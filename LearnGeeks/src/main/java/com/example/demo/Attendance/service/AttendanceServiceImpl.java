package com.example.demo.Attendance.service;

import com.example.demo.Attendance.DTO.*;
import com.example.demo.Attendance.model.*;
import com.example.demo.Attendance.repository.*;
import com.example.demo.SchoolStructure.Model.SchoolClass;
import com.example.demo.SchoolStructure.Model.Student;
import com.example.demo.SchoolStructure.Model.Subject;
import com.example.demo.SchoolStructure.Model.Teacher;
import com.example.demo.SchoolStructure.repository.SchoolClassRepository;
import com.example.demo.SchoolStructure.repository.StudentRepository;
import com.example.demo.SchoolStructure.repository.SubjectRepository;
import com.example.demo.SchoolStructure.repository.TeacherRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceSessionRepository sessionRepository;
    private final AttendanceRecordRepository recordRepository;

    private final SchoolClassRepository schoolClassRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    // 1️⃣ Create Attendance Session
    @Override
    public AttendanceSessionResponse createAttendanceSession(CreateAttendanceSessionRequest request) {

        sessionRepository.findBySchoolClassIdAndSubjectIdAndAttendanceDate(
                request.schoolClassId(),
                request.subjectId(),
                request.attendanceDate()
        ).ifPresent(session -> {
            throw new IllegalStateException("Attendance already created for this class, subject, and date");
        });

        SchoolClass schoolClass = schoolClassRepository.findById(request.schoolClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));

        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        AttendanceSession session = AttendanceSession.builder()
                .schoolClass(schoolClass)
                .subject(subject)
                .teacher(teacher)
                .attendanceDate(request.attendanceDate())
                .build();

        AttendanceSession saved = sessionRepository.save(session);

        return new AttendanceSessionResponse(
                saved.getId(),
                schoolClass.getId(),
                schoolClass.getName(),
                subject.getId(),
                subject.getName(),
                teacher.getId(),
                teacher.getFullName(),
                saved.getAttendanceDate()
        );
    }

    // 2️⃣ Mark Attendance
    @Override
    public void markAttendance(Long sessionId, List<MarkAttendanceRequest> requests) {

        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Attendance session not found"));

        for (MarkAttendanceRequest request : requests) {

            recordRepository.findByAttendanceSessionIdAndStudentId(
                    sessionId,
                    request.studentId()
            ).ifPresent(record -> {
                throw new IllegalStateException("Attendance already marked for student ID: " + request.studentId());
            });

            Student student = studentRepository.findById(request.studentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found"));

            AttendanceRecord record = AttendanceRecord.builder()
                    .attendanceSession(session)
                    .student(student)
                    .status(request.status())
                    .remarks(request.remarks())
                    .build();

            recordRepository.save(record);
        }
    }

    // 3️⃣ Get Attendance by Session
    @Override
    public List<AttendanceRecordResponse> getAttendanceBySession(Long sessionId) {

        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Attendance session not found"));

        return recordRepository.findByAttendanceSessionAttendanceDate(session.getAttendanceDate())
                .stream()
                .filter(r -> r.getAttendanceSession().getId().equals(sessionId))
                .map(r -> new AttendanceRecordResponse(
                        r.getStudent().getId(),
                        r.getStudent().getFullName(),
                        r.getStatus(),
                        r.getRemarks()
                ))
                .toList();
    }

    // 4️⃣ Student Attendance Summary
    @Override
    public StudentAttendanceSummaryResponse getStudentAttendanceSummary(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate
    ) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        long present = recordRepository.countByStudentIdAndStatus(studentId, AttendanceStatus.PRESENT);
        long absent = recordRepository.countByStudentIdAndStatus(studentId, AttendanceStatus.ABSENT);
        long late = recordRepository.countByStudentIdAndStatus(studentId, AttendanceStatus.LATE);

        long total = present + absent + late;

        double percentage = total == 0 ? 0 : (present + late * 0.5) * 100.0 / total;

        return new StudentAttendanceSummaryResponse(
                student.getId(),
                student.getFullName(),
                present,
                absent,
                late,
                percentage
        );
    }

    // 5️⃣ Class Attendance Summary
    @Override
    public ClassAttendanceSummaryResponse getClassAttendanceSummary(Long classId) {

        SchoolClass schoolClass = schoolClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));

        List<Student> students = studentRepository.findBySchoolClassId(classId);

        if (students.isEmpty()) {
            return new ClassAttendanceSummaryResponse(
                    classId,
                    schoolClass.getName(),
                    0,
                    0
            );
        }

        double totalPercentage = 0;

        for (Student student : students) {
            StudentAttendanceSummaryResponse summary =
                    getStudentAttendanceSummary(student.getId(), null, null);
            totalPercentage += summary.attendancePercentage();
        }

        double average = totalPercentage / students.size();

        return new ClassAttendanceSummaryResponse(
                classId,
                schoolClass.getName(),
                students.size(),
                average
        );
    }
}
