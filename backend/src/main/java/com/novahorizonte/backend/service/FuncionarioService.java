package com.novahorizonte.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.novahorizonte.backend.model.Funcionario;

@Service
public class FuncionarioService {
    
    private final List<Funcionario> funcionarios = new ArrayList<>();
    
    public FuncionarioService() {
        // Inicializando com dados mockados
        funcionarios.add(new Funcionario(
            1L,
            "Carlos Silva",
            "Professor",
            "Engenharia de Software",
            LocalDate.of(2015, 3, 15),
            LocalDate.of(2021, 7, 10)
        ));
        
        funcionarios.add(new Funcionario(
            2L,
            "Ana Costa",
            "Coordenadora Acadêmica",
            "Ensino",
            LocalDate.of(2010, 8, 20),
            LocalDate.of(2020, 11, 5)
        ));
        
        funcionarios.add(new Funcionario(
            3L,
            "João Oliveira",
            "Zelador",
            "Infraestrutura",
            LocalDate.of(2018, 1, 10),
            LocalDate.of(2022, 5, 15)
        ));
        
        funcionarios.add(new Funcionario(
            4L,
            "Maria Santos",
            "Professora",
            "Administração",
            LocalDate.of(2012, 6, 1),
            LocalDate.of(2019, 9, 20)
        ));
        
        funcionarios.add(new Funcionario(
            5L,
            "Pedro Martins",
            "Técnico Administrativo",
            "Recursos Humanos",
            LocalDate.of(2019, 2, 15),
            LocalDate.of(2023, 3, 10)
        ));
    }
    
    /**
     * Retorna a lista de todos os funcionários
     */
    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }
    
    /**
     * Busca um funcionário pelo ID
     * @param id o ID do funcionário
     * @return o funcionário encontrado ou null se não existir
     */
    public Funcionario buscarPorId(Long id) {
        return funcionarios.stream()
            .filter(f -> f.getId().equals(id))
            .findFirst()
            .orElse(null);
    }
}
