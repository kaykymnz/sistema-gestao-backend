package com.novahorizonte.backend.chat.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.novahorizonte.backend.chat.dto.ChatResponse;
import com.novahorizonte.backend.chat.dto.ContextUsedResponse;
import com.novahorizonte.backend.chat.gateway.LlmGateway;
import com.novahorizonte.backend.chat.gateway.LlmResult;

@Service
public class ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    private final ChatContextService chatContextService;
    private final PromptBuilder promptBuilder;
    private final LlmGateway llmGateway;

    public ChatService(
        ChatContextService chatContextService,
        PromptBuilder promptBuilder,
        LlmGateway llmGateway
    ) {
        this.chatContextService = chatContextService;
        this.promptBuilder = promptBuilder;
        this.llmGateway = llmGateway;
    }

    public ChatResponse ask(String message, String sessionId) {
        String effectiveSessionId = (sessionId == null || sessionId.isBlank())
            ? UUID.randomUUID().toString()
            : sessionId;

        ChatContext context = chatContextService.buildContext();
        String prompt = promptBuilder.build(message, context);

        logAudit(effectiveSessionId, message, context);

        LlmResult result;
        try {
            result = llmGateway.generate(effectiveSessionId, prompt);
        } catch (Exception exception) {
            LOGGER.error("Falha no provider de IA. sessionId={}", effectiveSessionId, exception);
            result = new LlmResult(
                "Não foi possível completar a análise por IA neste momento. "
                    + "Como fallback seguro, priorize revisão dos processos críticos e revalide os indicadores-chave com a equipe de gestão.",
                0.25
            );
        }

        return new ChatResponse(
            effectiveSessionId,
            result.answer(),
            new ContextUsedResponse(
                context.dashboardResumo(),
                context.processosCriticos(),
                context.indicadoresPessoas(),
                context.planejamentoAtivo()
            ),
            clamp(result.confidence())
        );
    }

    private void logAudit(String sessionId, String message, ChatContext context) {
        LOGGER.info(
            "AUDIT_CHAT sessionId={} messageHash={} messageLength={} contextSizes={{processos:{}, pessoas:{}, planejamento:{}}}",
            sessionId,
            sha256(message),
            message.length(),
            context.processosCriticos().size(),
            context.indicadoresPessoas().size(),
            context.planejamentoAtivo().size()
        );
    }

    private String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(encodedHash).substring(0, 16);
        } catch (NoSuchAlgorithmException exception) {
            return "hash_unavailable";
        }
    }

    private double clamp(double confidence) {
        if (confidence < 0.0) {
            return 0.0;
        }
        if (confidence > 1.0) {
            return 1.0;
        }
        return confidence;
    }
}
