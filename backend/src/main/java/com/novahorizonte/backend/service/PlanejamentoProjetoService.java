package com.novahorizonte.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}
