package com.example.demo.communication.service;

import com.example.demo.communication.model.Conversation;
import com.example.demo.communication.model.Message;
import com.example.demo.communication.repository.MessageRepository;
// import com.example.demo.communication.service.ConversationService;
// import com.example.demo.communication.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    @Override
    public Message sendMessage(Long conversationId, Long senderId,
                               String senderRole, String content) {

        Conversation conversation =
                conversationService.getConversationById(conversationId);

        Message message = Message.builder()
                .conversation(conversation)
                .senderId(senderId)
                .senderRole(senderRole)
                .content(content)
                .build();

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }

    @Override
    public void markAsRead(Long messageId) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setRead(true);
        messageRepository.save(message);
    }
}
