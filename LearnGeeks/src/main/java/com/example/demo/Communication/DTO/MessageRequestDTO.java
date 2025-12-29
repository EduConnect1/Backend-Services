package com.example.demo.communication.dto;

import lombok.Data;

@Data
public class MessageRequestDTO {

    private Long conversationId;
    private Long senderId;
    private String senderRole; // PARENT or TEACHER
    private String content;
}
