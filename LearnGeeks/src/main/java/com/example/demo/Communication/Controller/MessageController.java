package com.example.demo.communication.controller;

import com.example.demo.communication.model.Message;
import com.example.demo.communication.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long conversationId,
            @RequestParam Long senderId,
            @RequestParam String senderRole,
            @RequestParam String content) {

        Message message = messageService.sendMessage(
                conversationId, senderId, senderRole, content);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable Long conversationId) {

        return ResponseEntity.ok(
                messageService.getMessages(conversationId));
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long messageId) {

        messageService.markAsRead(messageId);
        return ResponseEntity.ok().build();
    }
}
