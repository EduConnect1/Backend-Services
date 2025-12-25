package com.example.demo.Analytics.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalyticsRepository {


    @Query("SELECT COUNT(s.id) FROM Student s")
    long countStudents();

    @Query("""
        SELECT AVG(a.presentPercentage)
        FROM AttendanceSummary a
    """)
    Double averageAttendanceRate();

    @Query("""
        SELECT
        (COUNT(sub.id) * 1.0 / COUNT(a.id)) * 100
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
        SELECT a.date, AVG(a.presentPercentage)
        FROM AttendanceSummary a
        WHERE a.classId = :classId
        GROUP BY a.date
        ORDER BY a.date
    """)
    List<Object[]> AttendanceHeatMap(Long classId);


    @Query("""
        SELECT s.id, s.fullName,
               AVG(a.presentPercentage),
               AVG(p.completionPercentage)
        FROM Student s
        JOIN AttendanceSummary a ON a.studentId = s.id
        JOIN StudentCourseProgress p ON p.student.id = s.id
        GROUP BY s.id, s.fullName
        HAVING AVG(a.presentPercentage) < :threshold
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
