package com.novahorizonte.backend.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    
    private Long id;
    private String nome;
    private String funcao;
    private String departamento;
    private LocalDate dataAdmissao;
    private LocalDate dataUltimaPromocao;
    
}
