package com.example.demo.Communication.DTO;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDTO {

    private Long id;
    private Long senderId;
    private String senderRole;
    private String content;
    private boolean read;
    private LocalDateTime sentAt;
}
