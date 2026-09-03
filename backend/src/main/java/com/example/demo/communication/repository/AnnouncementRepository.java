package com.example.demo.communication.repository;

import com.example.demo.communication.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByTeacherId(Long teacherId);
}

