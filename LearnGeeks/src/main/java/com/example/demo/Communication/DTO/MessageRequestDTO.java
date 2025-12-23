package com.example.demo.Communication.DTO;

import lombok.Data;

@Data
public class MessageRequestDTO {

    private Long conversationId;
    private Long senderId;
    private String senderRole; // PARENT or TEACHER
    private String content;
}
