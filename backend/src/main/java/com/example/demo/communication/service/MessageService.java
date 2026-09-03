package com.example.demo.communication.service;

import com.example.demo.communication.model.Message;
import java.util.List;

public interface MessageService {

    Message sendMessage(Long conversationId, Long senderId,
                        String senderRole, String content);

    List<Message> getMessages(Long conversationId);

    void markAsRead(Long messageId);
}
