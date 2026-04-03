package com.novahorizonte.backend.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.novahorizonte.backend.chat.dto.ChatResponse;
import com.novahorizonte.backend.chat.gateway.LlmGateway;
import com.novahorizonte.backend.chat.gateway.LlmResult;
import com.novahorizonte.backend.chat.service.ChatContext;
import com.novahorizonte.backend.chat.service.ChatContextService;
import com.novahorizonte.backend.chat.service.ChatService;
import com.novahorizonte.backend.chat.service.PromptBuilder;

class ChatServiceTest {

    @Test
    void deveAplicarFallbackSeguroQuandoGatewayFalhar() {
        ChatContextService contextService = mock(ChatContextService.class);
        PromptBuilder promptBuilder = new PromptBuilder();
        LlmGateway llmGateway = mock(LlmGateway.class);

        when(contextService.buildContext()).thenReturn(new ChatContext(
            "Resumo",
            List.of("Processo crítico"),
            List.of("Indicador pessoa"),
            List.of("Projeto ativo")
        ));
        when(llmGateway.generate(org.mockito.ArgumentMatchers.anyString(), org.mockito.ArgumentMatchers.anyString()))
            .thenThrow(new IllegalStateException("erro"));

        ChatService chatService = new ChatService(contextService, promptBuilder, llmGateway);
        ChatResponse response = chatService.ask("mensagem", null);

        assertThat(response.answer()).contains("fallback seguro");
        assertThat(response.confidence()).isBetween(0.0, 1.0);
        assertThat(response.sessionId()).isNotBlank();
    }

    @Test
    void deveRetornarRespostaDoGatewayQuandoSucesso() {
        ChatContextService contextService = mock(ChatContextService.class);
        PromptBuilder promptBuilder = new PromptBuilder();
        LlmGateway llmGateway = mock(LlmGateway.class);

        when(contextService.buildContext()).thenReturn(new ChatContext(
            "Resumo",
            List.of("A"),
            List.of("B"),
            List.of("C")
        ));
        when(llmGateway.generate("sessao-123", org.mockito.ArgumentMatchers.anyString()))
            .thenReturn(new LlmResult("Resposta IA", 0.9));

        ChatService chatService = new ChatService(contextService, promptBuilder, llmGateway);
        ChatResponse response = chatService.ask("mensagem", "sessao-123");

        assertThat(response.sessionId()).isEqualTo("sessao-123");
        assertThat(response.answer()).isEqualTo("Resposta IA");
        assertThat(response.confidence()).isEqualTo(0.9);
    }
}
