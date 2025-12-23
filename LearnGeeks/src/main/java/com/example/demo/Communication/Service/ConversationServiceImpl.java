package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Conversation;
import com.example.demo.Communication.Repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation getOrCreateConversation(Long parentId, Long teacherId) {

        return conversationRepository
                .findByParentIdAndTeacherId(parentId, teacherId)
                .orElseGet(() -> conversationRepository.save(
                        Conversation.builder()
                                .parentId(parentId)
                                .teacherId(teacherId)
                                .build()
                ));
    }

    @Override
    public Conversation getConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() ->
                        new RuntimeException("Conversation not found"));
    }
}
