package com.example.demo.Communication.DTO;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnnouncementResponseDTO {

    private Long id;
    private String title;
    private String message;
    private String postedBy;
    private LocalDateTime createdAt;
}

