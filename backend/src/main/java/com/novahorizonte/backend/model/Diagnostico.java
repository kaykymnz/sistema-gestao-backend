package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Diagnóstico organizacional com achados e oportunidades
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {
    private String resumoExecutivo;
    private List<AchadoDiagnostico> achados;
    private List<String> pontosFortesIdentificados;
    private List<String> areaCritica;
    private List<String> oportunidadesIdentificadas;
}
