package com.novahorizonte.backend.chat.service;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String build(String userMessage, ChatContext context) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Você é um assistente de gestão universitária. ")
            .append("Responda de forma objetiva, orientada a ação e baseada apenas no contexto fornecido.\n\n")
            .append("# Contexto Institucional\n")
            .append("Dashboard: ").append(context.dashboardResumo()).append("\n")
            .append("Processos críticos: ").append(String.join("; ", context.processosCriticos())).append("\n")
            .append("Indicadores de pessoas: ").append(String.join("; ", context.indicadoresPessoas())).append("\n")
            .append("Planejamento ativo: ").append(String.join("; ", context.planejamentoAtivo())).append("\n\n")
            .append("# Pergunta do gestor\n")
            .append(userMessage).append("\n\n")
            .append("# Formato desejado\n")
            .append("- Diagnóstico curto\n")
            .append("- 3 recomendações práticas\n")
            .append("- Próximo passo em até 7 dias");

        return prompt.toString();
    }
}
