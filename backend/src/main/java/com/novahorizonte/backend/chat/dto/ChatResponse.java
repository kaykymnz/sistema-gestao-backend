package com.novahorizonte.backend.chat.dto;

public record ChatResponse(
    String sessionId,
    String answer,
    ContextUsedResponse contextUsed,
    double confidence
) {
}
