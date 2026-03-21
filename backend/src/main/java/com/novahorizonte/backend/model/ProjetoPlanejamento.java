package com.novahorizonte.backend.model;

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
public class ProjetoPlanejamento {

    private Long id;
    private String titulo;
    private String status;
    private String prioridade;
    private String descricao;
    private String responsavel;
    private LocalDate dataCriacao;
    private FiveW2H fiveW2H;
    private List<String> oportunidadesMelhoriaIa;
}
