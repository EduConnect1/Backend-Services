package com.example.demo.communication.service;

import com.example.demo.communication.model.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement createAnnouncement(String title, String message, Long teacherId);

    List<Announcement> getAllAnnouncements();
}
