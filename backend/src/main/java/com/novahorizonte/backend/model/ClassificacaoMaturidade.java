package com.novahorizonte.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classificação de maturidade organizacional com trajetória
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoMaturidade {
    private Integer score; // 0-100
    private String niveau; // "iniciante", "em_desenvolvimento", "operacional", "otimizado", "lider_industria"
    private Integer comparativoSetor;
    private TrajetoriaMelhoria trajetoriaMelhoria;
}
