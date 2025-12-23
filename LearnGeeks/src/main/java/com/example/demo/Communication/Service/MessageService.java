package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Message;
import java.util.List;

public interface MessageService {

    Message sendMessage(Long conversationId, Long senderId,
                        String senderRole, String content);

    List<Message> getMessages(Long conversationId);

    void markAsRead(Long messageId);
}
