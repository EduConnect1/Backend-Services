package com.example.demo.communication.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversationResponseDTO {

    private Long conversationId;
    private Long parentId;
    private Long teacherId;
    private String lastMessage;
    private LocalDateTime lastUpdated;
}

