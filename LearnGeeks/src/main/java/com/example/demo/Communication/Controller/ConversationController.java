package com.example.demo.Communication.Controller;

import com.example.demo.Communication.Model.Conversation;
import com.example.demo.Communication.Service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @PostMapping("/start")
    public ResponseEntity<Conversation> startConversation(
            @RequestParam Long parentId,
            @RequestParam Long teacherId) {

        Conversation conversation =
                conversationService.getOrCreateConversation(parentId, teacherId);

        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversation(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                conversationService.getConversationById(id));
    }
}

