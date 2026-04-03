package com.novahorizonte.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatrizRiscoDto {

    private String titulo;
    private Double probabilidade;
    private Double impacto;
    private String nivel;
    private String mitigacao;
}
