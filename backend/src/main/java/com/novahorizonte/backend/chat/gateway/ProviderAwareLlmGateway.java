package com.novahorizonte.backend.chat.gateway;

import java.util.Locale;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.novahorizonte.backend.chat.config.AiProperties;

@Component
@Primary
public class ProviderAwareLlmGateway implements LlmGateway {

    private final AiProperties aiProperties;
    private final MockLlmGateway mockLlmGateway;
    private final OpenAiCompatibleLlmGateway openAiCompatibleLlmGateway;

    public ProviderAwareLlmGateway(
        AiProperties aiProperties,
        MockLlmGateway mockLlmGateway,
        OpenAiCompatibleLlmGateway openAiCompatibleLlmGateway
    ) {
        this.aiProperties = aiProperties;
        this.mockLlmGateway = mockLlmGateway;
        this.openAiCompatibleLlmGateway = openAiCompatibleLlmGateway;
    }

    @Override
    public LlmResult generate(String sessionId, String prompt) {
        String provider = aiProperties.getProvider() == null
            ? "mock"
            : aiProperties.getProvider().toLowerCase(Locale.ROOT);

        return switch (provider) {
            case "openai" -> openAiCompatibleLlmGateway.generate(sessionId, prompt);
            case "mock" -> mockLlmGateway.generate(sessionId, prompt);
            default -> throw new IllegalArgumentException("AI_PROVIDER inválido: " + provider);
        };
    }
}
