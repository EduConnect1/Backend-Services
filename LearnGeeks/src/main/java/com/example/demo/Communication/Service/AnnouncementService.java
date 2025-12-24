package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Announcement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnnouncementService {

    Announcement createAnnouncement(String title, String message, Long teacherId);

    List<Announcement> getAllAnnouncements();
}
