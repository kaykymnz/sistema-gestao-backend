package com.novahorizonte.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanejamentoEstrategicoDto {

    private String id;
    private String titulo;
    private String descricao;
    private PeriodDto periodo;
    private List<ObjetivoEstrategicoDto> objetivos;
    private SWOTDto swot;
    private String statusGeral;
    private Double percentualConclusao;
    private LocalDate ultimaAtualizacao;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PeriodDto {
        private LocalDate dataInicio;
        private LocalDate dataFim;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ObjetivoEstrategicoDto {
        private String id;
        private String titulo;
        private String descricao;
        private List<MetaEstrategicaDto> metas;
        private String responsavel;
        private String status;
        private Double percentualConclusao;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaEstrategicaDto {
        private String id;
        private String titulo;
        private Double valorEsperado;
        private Double valorAlcancado;
        private String unidade;
        private LocalDate dataLimite;
        private String status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SWOTDto {
        private List<String> forcas;
        private List<String> fraquezas;
        private List<String> oportunidades;
        private List<String> ameacas;
    }
}
