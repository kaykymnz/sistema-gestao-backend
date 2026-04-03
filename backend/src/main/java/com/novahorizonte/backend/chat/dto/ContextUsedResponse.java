package com.novahorizonte.backend.chat.dto;

import java.util.List;

public record ContextUsedResponse(
    String dashboardResumo,
    List<String> processosCriticos,
    List<String> indicadoresPessoas,
    List<String> planejamentoAtivo
) {
}
