package com.example.demo.analytics.service;



import com.example.demo.analytics.dto.*;
import com.example.demo.analytics.repository.AnalyticsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Override
    public AnalyticsOverviewResponse getOverview() {

        return new AnalyticsOverviewResponse(
                analyticsRepository.countStudents(),
                safe(analyticsRepository.averageAttendanceRate()),
                safe(analyticsRepository.assignmentCompletionRate()),
                safe(analyticsRepository.averageCourseCompletion())
        );
    }

    @Override
    public List<AttendanceHeatMapResponse> getAttendanceHeatmap(Long classId) {

        return analyticsRepository.AttendanceHeatMap(classId)
                .stream()
                .map(r -> new AttendanceHeatMapResponse(
                        (java.time.LocalDate) r[0],
                        (Double) r[1]
                ))
                .toList();
    }

    @Override
    public List<AtRiskStudentResponse> getAtRiskStudents(double threshold) {

        return analyticsRepository.atRiskStudents(threshold)
                .stream()
                .map(r -> new AtRiskStudentResponse(
                        (Long) r[0],
                        (String) r[1] + " " + (String) r[2],
                        (Double) r[3],
                        (Double) r[4]
                ))
                .toList();
    }

    @Override
    public List<CourseEngagementResponse> getCourseEngagement() {

        return analyticsRepository.courseEngagement()
                .stream()
                .map(r -> new CourseEngagementResponse(
                        (Long) r[0],
                        (String) r[1],
                        (Double) r[2],
                        (Long) r[3]
                ))
                .toList();
    }

    private double safe(Double value) {
        return value == null ? 0 : value;
    }
}
