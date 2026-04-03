package com.novahorizonte.backend.chat.gateway;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novahorizonte.backend.chat.config.AiProperties;

@Component
public class OpenAiCompatibleLlmGateway implements LlmGateway {

    private static final String OPENAI_CHAT_URL = "https://api.openai.com/v1/chat/completions";

    private final AiProperties aiProperties;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public OpenAiCompatibleLlmGateway(AiProperties aiProperties, ObjectMapper objectMapper) {
        this.aiProperties = aiProperties;
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMillis(aiProperties.getTimeoutMs()))
            .build();
    }

    @Override
    public LlmResult generate(String sessionId, String prompt) {
        if (aiProperties.getApiKey() == null || aiProperties.getApiKey().isBlank()) {
            throw new IllegalStateException("AI_API_KEY não configurada para provider openai");
        }

        try {
            String body = objectMapper.writeValueAsString(Map.of(
                "model", aiProperties.getModel(),
                "temperature", aiProperties.getTemperature(),
                "max_tokens", aiProperties.getMaxTokens(),
                "messages", List.of(
                    Map.of("role", "system", "content", "Você é um copiloto de gestão organizacional."),
                    Map.of("role", "user", "content", prompt)
                )
            ));

            HttpRequest request = HttpRequest.newBuilder(URI.create(OPENAI_CHAT_URL))
                .header("Authorization", "Bearer " + aiProperties.getApiKey())
                .header("Content-Type", "application/json")
                .timeout(Duration.ofMillis(aiProperties.getTimeoutMs()))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                throw new IllegalStateException("Erro do provedor LLM status=" + response.statusCode());
            }

            JsonNode root = objectMapper.readTree(response.body());
            JsonNode firstChoice = root.path("choices").path(0);
            String answer = firstChoice.path("message").path("content").asText();
            if (answer == null || answer.isBlank()) {
                throw new IllegalStateException("Resposta vazia do provedor LLM");
            }

            double confidence = firstChoice.path("finish_reason").asText("stop").equals("stop") ? 0.82 : 0.65;
            return new LlmResult(answer, confidence);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Falha ao chamar provedor LLM", exception);
        } catch (IOException exception) {
            throw new IllegalStateException("Falha ao chamar provedor LLM", exception);
        }
    }
}
