package com.novahorizonte.backend.chat.gateway;

import org.springframework.stereotype.Component;

@Component
public class MockLlmGateway implements LlmGateway {

    @Override
    public LlmResult generate(String sessionId, String prompt) {
        String answer = "Diagnóstico curto: os dados apontam oportunidade de acelerar processos críticos e consolidar indicadores. "
            + "Recomendações: (1) priorizar automação de validações, (2) definir painel semanal de pessoas, "
            + "(3) acompanhar status dos projetos de alta prioridade. "
            + "Próximo passo: realizar reunião executiva com responsáveis em até 7 dias.";
        return new LlmResult(answer, 0.74);
    }
}
