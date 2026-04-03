package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Trajetória de melhoria de maturidade ao longo do tempo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrajetoriaMelhoria {
    private LocalDate dataAtual;
    private Integer scoreAtual;
    private Integer scoreProjetado12Meses;
    private List<String> caminhoMelhor;
}
