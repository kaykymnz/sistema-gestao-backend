package com.novahorizonte.backend.chat;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.novahorizonte.backend.chat.service.ChatContext;
import com.novahorizonte.backend.chat.service.PromptBuilder;

class PromptBuilderTest {

    private final PromptBuilder promptBuilder = new PromptBuilder();

    @Test
    void deveMontarPromptEstruturadoComContexto() {
        ChatContext context = new ChatContext(
            "Resumo do dashboard",
            List.of("Processo A", "Processo B"),
            List.of("Pessoas KPI 1"),
            List.of("Projeto X")
        );

        String prompt = promptBuilder.build("Como melhorar?", context);

        assertThat(prompt)
            .contains("# Contexto Institucional")
            .contains("Resumo do dashboard")
            .contains("Processo A")
            .contains("Pessoas KPI 1")
            .contains("Projeto X")
            .contains("Como melhorar?");
    }
}
