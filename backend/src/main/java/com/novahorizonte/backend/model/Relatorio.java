package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Modelo de Relatório Inteligente
 * Gerado por análise de IA para apoio à decisão estratégica
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataUltimaAtualizacao;
    private String tipo; // "diagnostico", "analise", "predicao"
    private Integer confiabilidade; // 0-100
    private String responsavel;
    private String status; // "rascunho", "publicado", "arquivado"
    
    // Dados do relatório
    private Diagnostico diagnostico;
    private List<SugestoMelhoria> sugestoes;
    private ClassificacaoMaturidade classificacaoMaturidade;
}
