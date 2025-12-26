package com.example.demo.communication.dto;

import lombok.Data;

@Data
public class AnnouncementRequestDTO {

    private String title;
    private String message;
    private Long teacherId;
}
