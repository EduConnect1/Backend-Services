package com.example.demo.analytics.repository;

import com.example.demo.analytics.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {


    @Query("SELECT COUNT(s.id) FROM Student s")
    long countStudents();

    @Query("SELECT COUNT(ar.id) FROM AttendanceRecord ar WHERE ar.status = 'PRESENT'")
    Long countPresentRecords();

    @Query("SELECT COUNT(ar.id) FROM AttendanceRecord ar")
    Long countTotalAttendanceRecords();

    default Double averageAttendanceRate() {
        Long total = countTotalAttendanceRecords();
        if (total == null || total == 0) return 0.0;
        Long present = countPresentRecords();
        return (present * 100.0) / total;
    }

    @Query("""
        SELECT
        (COUNT(sub.id) * 1.0 / NULLIF(COUNT(a.id), 0)) * 100
        FROM Assignment a
        LEFT JOIN AssignmentSubmission sub ON sub.assignment.id = a.id
    """)
    Double assignmentCompletionRate();

    @Query("""
        SELECT AVG(p.completionPercentage)
        FROM StudentCourseProgress p
    """)
    Double averageCourseCompletion();


    @Query("""
        SELECT asess.attendanceDate, 
               (SUM(CASE WHEN ar.status = 'PRESENT' THEN 1.0 ELSE 0.0 END) / COUNT(ar.id)) * 100
        FROM AttendanceSession asess
        JOIN AttendanceRecord ar ON ar.attendanceSession.id = asess.id
        WHERE asess.schoolClass.id = :classId
        GROUP BY asess.attendanceDate
        ORDER BY asess.attendanceDate
    """)
    List<Object[]> AttendanceHeatMap(Long classId);


    @Query("""
        SELECT s.id, 
               s.user.firstName, 
               s.user.lastName,
               (SUM(CASE WHEN ar.status = 'PRESENT' THEN 1.0 ELSE 0.0 END) / COUNT(ar.id)) * 100,
               AVG(p.completionPercentage)
        FROM Student s
        JOIN AttendanceRecord ar ON ar.student.id = s.id
        LEFT JOIN StudentCourseProgress p ON p.student.id = s.id
        GROUP BY s.id, s.user.firstName, s.user.lastName
        HAVING (SUM(CASE WHEN ar.status = 'PRESENT' THEN 1.0 ELSE 0.0 END) / COUNT(ar.id)) * 100 < :threshold
    """)
    List<Object[]> atRiskStudents(double threshold);

    @Query("""
        SELECT c.id, c.title,
               AVG(p.completionPercentage),
               COUNT(p.student.id)
        FROM Course c
        JOIN StudentCourseProgress p ON p.course.id = c.id
        GROUP BY c.id, c.title
    """)
    List<Object[]> courseEngagement();
}
