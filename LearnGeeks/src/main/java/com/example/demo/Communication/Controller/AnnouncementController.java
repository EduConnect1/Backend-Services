package com.example.demo.Communication.Controller;

import com.example.demo.Communication.Model.Announcement;
import com.example.demo.Communication.Service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(
            @RequestParam String title,
            @RequestParam String message,
            @RequestParam Long teacherId) {

        Announcement announcement =
                announcementService.createAnnouncement(title, message, teacherId);

        return ResponseEntity.ok(announcement);
    }

    @GetMapping
    public ResponseEntity<List<Announcement>> getAnnouncements() {
        return ResponseEntity.ok(
                announcementService.getAllAnnouncements());
    }
}
