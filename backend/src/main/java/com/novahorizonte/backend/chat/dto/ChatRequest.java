package com.novahorizonte.backend.chat.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
    @NotBlank(message = "message é obrigatório")
    String message,
    String sessionId
) {
}
