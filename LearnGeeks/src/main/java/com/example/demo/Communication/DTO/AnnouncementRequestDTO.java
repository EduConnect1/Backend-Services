package com.example.demo.Communication.DTO;

import lombok.Data;

@Data
public class AnnouncementRequestDTO {

    private String title;
    private String message;
    private Long teacherId;
}
