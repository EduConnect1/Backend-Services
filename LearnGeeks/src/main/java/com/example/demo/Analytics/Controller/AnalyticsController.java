package com.example.demo.Analytics.Controller;
import com.example.demo.Analytics.DTO.*;
import com.example.demo.Analytics.Service.AnalyticsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/overview")
    public AnalyticsOverviewResponse overview() {
        return analyticsService.getOverview();
    }

    @GetMapping("/attendance/heatmap")
    public List<AttendanceHeatMapResponse> attendanceHeatmap(
            @RequestParam Long classId) {

        return analyticsService.getAttendanceHeatmap(classId);
    }

    @GetMapping("/at-risk-students")
    public List<AtRiskStudentResponse> atRiskStudents(
            @RequestParam(defaultValue = "75") double threshold) {

        return analyticsService.getAtRiskStudents(threshold);
    }

    @GetMapping("/course-engagement")
    public List<CourseEngagementResponse> courseEngagement() {

        return analyticsService.getCourseEngagement();
    }
}

