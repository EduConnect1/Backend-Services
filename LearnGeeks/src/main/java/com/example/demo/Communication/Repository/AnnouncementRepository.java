package com.example.demo.Communication.Repository;

import com.example.demo.Communication.Model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByTeacherId(Long teacherId);
}

