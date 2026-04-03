package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Sugestão de melhoria com prioridade, impacto e ROI
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SugestoMelhoria {
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade; // "alta", "media", "baixa"
    private String impactoEstimado; // "transformador", "significativo", "moderado"
    private Integer esforco; // 0-100
    private Integer retornoOnInvestimento; // 0-100
    private Integer diasParaImplementacao;
    private String responsavel;
    private String status; // "proposta", "em_analise", "aprovada", "implementando", "concluida"
}
