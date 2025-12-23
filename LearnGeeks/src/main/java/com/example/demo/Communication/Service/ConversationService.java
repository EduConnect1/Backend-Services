package com.example.demo.Communication.Service;

import com.example.demo.Communication.Model.Conversation;

public interface ConversationService {

    Conversation getOrCreateConversation(Long parentId, Long teacherId);

    Conversation getConversationById(Long conversationId);
}
