package com.example.demo.Analytics.Service;

import com.example.demo.Analytics.DTO.*;

import java.util.List;

public interface AnalyticsService {

    AnalyticsOverviewResponse getOverview();

    List<AttendanceHeatMapResponse> getAttendanceHeatmap(Long classId);

    List<AtRiskStudentResponse> getAtRiskStudents(double threshold);

    List<CourseEngagementResponse> getCourseEngagement();
}

