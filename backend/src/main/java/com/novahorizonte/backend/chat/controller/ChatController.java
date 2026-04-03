package com.novahorizonte.backend.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novahorizonte.backend.chat.dto.ChatRequest;
import com.novahorizonte.backend.chat.dto.ChatResponse;
import com.novahorizonte.backend.chat.service.ChatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        ChatResponse response = chatService.ask(request.message(), request.sessionId());
        return ResponseEntity.ok(response);
    }
}
