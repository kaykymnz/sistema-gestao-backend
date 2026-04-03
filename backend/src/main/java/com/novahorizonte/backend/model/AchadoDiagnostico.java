package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Achado específico do diagnóstico com impacto e evidência
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AchadoDiagnostico {
    private String titulo;
    private String descricao;
    private String impacto; // "alto", "medio", "baixo"
    private String evidencia;
    private String categoria;
}
