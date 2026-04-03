package com.novahorizonte.backend.chat.gateway;

public interface LlmGateway {
    LlmResult generate(String sessionId, String prompt);
}
