package com.example.demo.analytics.service;

import com.example.demo.analytics.dto.*;

import java.util.List;

public interface AnalyticsService {

    AnalyticsOverviewResponse getOverview();

    List<AttendanceHeatMapResponse> getAttendanceHeatmap(Long classId);

    List<AtRiskStudentResponse> getAtRiskStudents(double threshold);

    List<CourseEngagementResponse> getCourseEngagement();
}

