package com.novahorizonte.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.novahorizonte.backend.dto.PlanoAcao5W2HDto;
import com.novahorizonte.backend.model.FiveW2H;
import com.novahorizonte.backend.model.ProjetoPlanejamento;

@Service
public class PlanejamentoProjetoService {

    private final List<ProjetoPlanejamento> projetos = new ArrayList<>();

    public PlanejamentoProjetoService() {
        projetos.add(new ProjetoPlanejamento(
            1L,
            "Programa de tutoria para retenção de alunos",
            "Em andamento",
            "Alta",
            "Estruturar um programa de acompanhamento para apoiar alunos com maior risco de evasão.",
            "Coordenação Acadêmica",
            LocalDate.of(2026, 3, 1),
            new FiveW2H(
                "Implantar um programa de tutoria acadêmica com acompanhamento quinzenal.",
                "Reduzir evasão, melhorar desempenho e ampliar o engajamento dos estudantes.",
                "Campus principal e ambiente virtual da instituição.",
                LocalDate.of(2026, 4, 15),
                "Coordenação Acadêmica, tutores e professores orientadores.",
                "Selecionar tutores, definir critérios de acompanhamento e acompanhar indicadores de frequência e notas.",
                new BigDecimal("18500.00")
            ),
            List.of(
                "Priorizar turmas com maior índice de reprovação para a fase piloto.",
                "Definir métricas de sucesso por ciclo para facilitar futuras recomendações por IA."
            )
        ));

        projetos.add(new ProjetoPlanejamento(
            2L,
            "Digitalização do fluxo de solicitações internas",
            "Planejado",
            "Média",
            "Mapear e simplificar o processo de abertura, aprovação e acompanhamento de solicitações administrativas.",
            "Gerência Administrativa",
            LocalDate.of(2026, 2, 20),
            new FiveW2H(
                "Criar um fluxo digital centralizado para solicitações internas.",
                "Diminuir retrabalho, reduzir tempo de resposta e melhorar rastreabilidade.",
                "Setores administrativos, secretaria e portal interno.",
                LocalDate.of(2026, 5, 10),
                "Gerência Administrativa, TI e líderes de setor.",
                "Levantar gargalos, desenhar fluxo futuro, validar com as áreas e disponibilizar acompanhamento por status.",
                new BigDecimal("9600.00")
            ),
            List.of(
                "Padronizar categorias de solicitação para melhorar futuras análises automáticas.",
                "Registrar tempos por etapa para permitir sugestões de otimização baseadas em dados."
            )
        ));
    }

    public List<ProjetoPlanejamento> listarProjetos() {
        return new ArrayList<>(projetos);
    }

    public ProjetoPlanejamento buscarPorId(Long id) {
        return projetos.stream()
            .filter(projeto -> projeto.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<PlanoAcao5W2HDto> listarPlanos5W2H() {
        return projetos.stream()
            .map(this::converterParaPlanoAcao5W2H)
            .collect(Collectors.toList());
    }

    private PlanoAcao5W2HDto converterParaPlanoAcao5W2H(ProjetoPlanejamento projeto) {
        PlanoAcao5W2HDto dto = new PlanoAcao5W2HDto();
        
        // Mapeamento de dados básicos do projeto
        dto.setId(String.valueOf(projeto.getId()));
        dto.setTitulo(projeto.getTitulo());
        dto.setObjetivo(projeto.getDescricao());
        dto.setResponsavel(projeto.getResponsavel());
        dto.setStatus(projeto.getStatus());
        dto.setPrioridade(projeto.getPrioridade());
        dto.setPrazo(projeto.getDataCriacao());
        
        // Mapeamento dos dados de 5W2H
        if (projeto.getFiveW2H() != null) {
            FiveW2H fiveW2H = projeto.getFiveW2H();
            
            dto.setOQue(fiveW2H.getWhat());
            dto.setPorQue(fiveW2H.getWhy());
            dto.setOnde(fiveW2H.getWhere());
            dto.setQuando(fiveW2H.getWhen());
            dto.setQuem(fiveW2H.getWho());
            dto.setComo(fiveW2H.getHow());
            dto.setQuanto(fiveW2H.getHowMuch() != null ? fiveW2H.getHowMuch().doubleValue() : 0.0);
            dto.setCustoEstimado(fiveW2H.getHowMuch() != null ? fiveW2H.getHowMuch().doubleValue() : 0.0);
            dto.setMoeda("BRL");
        }
        
        // Mapeamento de indicadores
        if (projeto.getOportunidadesMelhoriaIa() != null && !projeto.getOportunidadesMelhoriaIa().isEmpty()) {
            dto.setIndicadores(projeto.getOportunidadesMelhoriaIa());
        }
        
        // Valores padrão para campos opcionais
        dto.setProgresso(50.0); // Progresso padrão
        
        return dto;
    }
}
