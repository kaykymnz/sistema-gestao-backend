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
public class PlanoAcao5W2HDto {

    private String id;
    private String titulo;
    private String objetivo;
    private String oQue;
    private String porQue;
    private String onde;
    private LocalDate quando;
    private String quem;
    private String como;
    private Double quanto;
    private String responsavel;
    private LocalDate prazo;
    private Double custoEstimado;
    private String moeda;
    private String prioridade;
    private String status;
    private Double progresso;
    private List<String> indicadores;
    private String observacoes;
}
