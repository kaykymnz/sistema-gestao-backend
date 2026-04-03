package com.novahorizonte.backend.chat.service;

import java.util.List;

public record ChatContext(
    String dashboardResumo,
    List<String> processosCriticos,
    List<String> indicadoresPessoas,
    List<String> planejamentoAtivo
) {
}
