package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Conversation;
import com.example.demo.Communication.Model.Message;
import com.example.demo.Communication.Repository.MessageRepository;
// import com.example.demo.Communication.Service.ConversationService;
// import com.example.demo.Communication.Service.MessageService;
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
