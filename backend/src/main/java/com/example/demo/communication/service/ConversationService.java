package com.example.demo.communication.service;

import com.example.demo.communication.model.Conversation;

public interface ConversationService {

    Conversation getOrCreateConversation(Long parentId, Long teacherId);

    Conversation getConversationById(Long conversationId);
}
