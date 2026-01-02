package com.example.demo.communication.controller;

import com.example.demo.communication.model.Message;
import com.example.demo.communication.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    // =========================
    // POST /api/messages/send
    // =========================
    @Test
    void shouldSendMessage() throws Exception {

        Message message = new Message();
        message.setId(10L);
        message.setContent("Hello teacher");
        message.setSenderId(2L);
        message.setSenderRole("STUDENT");
        message.setRead(false);

        when(messageService.sendMessage(eq(1L), eq(2L), eq("STUDENT"), eq("Hello teacher")))
                .thenReturn(message);

        mockMvc.perform(post("/api/messages/send")
                .param("conversationId", "1")
                .param("senderId", "2")
                .param("senderRole", "STUDENT")
                .param("content", "Hello teacher")
                .contentType(MediaType.APPLICATION_JSON)) // Optional content type, but good practice
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.content").value("Hello teacher"))
                .andExpect(jsonPath("$.read").value(false));
    }

    // =========================
    // PUT /api/messages/{messageId}/read
    // =========================
    @Test
    void shouldMarkMessageAsRead() throws Exception {

        doNothing().when(messageService).markAsRead(1L);

        mockMvc.perform(put("/api/messages/1/read"))
                .andExpect(status().isOk());
    }

    // =========================
    // GET /api/messages/{conversationId}
    // =========================
    @Test
    void shouldGetMessagesByConversation() throws Exception {

        Message msg1 = new Message();
        msg1.setId(1L);
        msg1.setContent("Hi");
        msg1.setRead(true);

        Message msg2 = new Message();
        msg2.setId(2L);
        msg2.setContent("Hello");
        msg2.setRead(false);

        when(messageService.getMessages(1L))
                .thenReturn(List.of(msg1, msg2));

        mockMvc.perform(get("/api/messages/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].content").value("Hi"))
                .andExpect(jsonPath("$[1].read").value(false));
    }
}
