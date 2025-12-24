package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Announcement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    public Announcement createAnnouncement(String title, String message, Long teacherId){
        return null;
    }

    public List<Announcement> getAllAnnouncements(){
        return null;
    }
}
