package com.novahorizonte.backend.service;

import com.novahorizonte.backend.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço de Relatórios Inteligentes com dados mockados
 * Responsável por gerenciar geração, recuperação e transformação de relatórios
 */
@Service
public class RelatorioService {
    private final List<Relatorio> relatorios = new ArrayList<>();
    private Long ultimoId = 2L;

    public RelatorioService() {
        inicializarDadosMockados();
    }

    /**
     * Inicializa dados mockados de relatórios
     */
    private void inicializarDadosMockados() {
        // Relatório 1: Diagnóstico Estratégico Institucional
        relatorios.add(criarRelatorioCompleto(
            1L,
            "Diagnóstico Estratégico Institucional - 2026",
            "Análise completa da maturidade organizacional da UNH",
            "diagnostico",
            92,
            "Pró-Reitoria de Planejamento",
            criarDiagnosticoEstrategico(),
            criarSugestoesEstrategicas(),
            criarMaturidadeEstrategica()
        ));

        // Relatório 2: Análise de Processos Críticos
        relatorios.add(criarRelatorioCompleto(
            2L,
            "Análise de Processos Críticos - Matrícula e Registros",
            "Mapeamento detalhado do fluxo de matrícula com identificação de gargalos",
            "analise",
            88,
            "Coordenação Acadêmica",
            criarDiagnosticoProcessos(),
            criarSugestoesProcessos(),
            criarMaturidadeProcessos()
        ));
    }

    /**
     * Lista todos os relatórios
     */
    public List<Relatorio> listarRelatorios() {
        return new ArrayList<>(relatorios);
    }

    /**
     * Busca relatório por ID
     */
    public Optional<Relatorio> buscarPorId(Long id) {
        return relatorios.stream()
            .filter(r -> r.getId().equals(id))
            .findFirst();
    }

    /**
     * Busca relatórios por tipo
     */
    public List<Relatorio> buscarPorTipo(String tipo) {
        return relatorios.stream()
            .filter(r -> r.getTipo().equalsIgnoreCase(tipo))
            .collect(Collectors.toList());
    }

    /**
     * Cria novo relatório (POST)
     * Simula processamento por IA
     */
    public Relatorio criarRelatorio(Relatorio novoRelatorio) {
        novoRelatorio.setId(++ultimoId);
        novoRelatorio.setDataCriacao(LocalDate.now());
        novoRelatorio.setDataUltimaAtualizacao(LocalDate.now());
        novoRelatorio.setStatus("publicado");
        
        if (novoRelatorio.getConfiabilidade() == null) {
            novoRelatorio.setConfiabilidade(85);
        }

        relatorios.add(novoRelatorio);
        return novoRelatorio;
    }

    /**
     * Atualiza relatório existente (PUT)
     */
    public Optional<Relatorio> atualizarRelatorio(Long id, Relatorio relatarioAtualizado) {
        Optional<Relatorio> relatorioExistente = buscarPorId(id);

        if (relatorioExistente.isPresent()) {
            Relatorio r = relatorioExistente.get();
            r.setTitulo(relatarioAtualizado.getTitulo() != null ? relatarioAtualizado.getTitulo() : r.getTitulo());
            r.setDescricao(relatarioAtualizado.getDescricao() != null ? relatarioAtualizado.getDescricao() : r.getDescricao());
            r.setDataUltimaAtualizacao(LocalDate.now());
            r.setStatus(relatarioAtualizado.getStatus() != null ? relatarioAtualizado.getStatus() : r.getStatus());
            r.setDiagnostico(relatarioAtualizado.getDiagnostico() != null ? relatarioAtualizado.getDiagnostico() : r.getDiagnostico());
            r.setSugestoes(relatarioAtualizado.getSugestoes() != null ? relatarioAtualizado.getSugestoes() : r.getSugestoes());
        }

        return relatorioExistente;
    }

    /**
     * Deleta relatório por ID
     */
    public boolean deletarRelatorio(Long id) {
        return relatorios.removeIf(r -> r.getId().equals(id));
    }

    // ==================== HELPERS DE CRIAÇÃO ====================

    private Relatorio criarRelatorioCompleto(
        Long id,
        String titulo,
        String descricao,
        String tipo,
        Integer confiabilidade,
        String responsavel,
        Diagnostico diagnostico,
        List<SugestoMelhoria> sugestoes,
        ClassificacaoMaturidade maturidade
    ) {
        Relatorio r = new Relatorio();
        r.setId(id);
        r.setTitulo(titulo);
        r.setDescricao(descricao);
        r.setTipo(tipo);
        r.setConfiabilidade(confiabilidade);
        r.setResponsavel(responsavel);
        r.setDiagnostico(diagnostico);
        r.setSugestoes(sugestoes);
        r.setClassificacaoMaturidade(maturidade);
        r.setDataCriacao(LocalDate.of(2026, 3, 15));
        r.setDataUltimaAtualizacao(LocalDate.of(2026, 3, 20));
        r.setStatus("publicado");
        return r;
    }

    private Diagnostico criarDiagnosticoEstrategico() {
        Diagnostico d = new Diagnostico();
        d.setResumoExecutivo(
            "A Universidade Nova Horizonte apresenta maturidade organizacional em nível de desenvolvimento, com score de 68/100. " +
            "Principais desafios identificados: processos administrativos fragmentados, falta de integração de dados estratégicos e comunicação interna descentralizada. " +
            "Oportunidades: implementação de plataforma integrada, standardização de processos e melhor engajamento."
        );

        d.setAchados(List.of(
            new AchadoDiagnostico("Processos administrativos fragmentados",
                "Matrícula, aprovação de disciplinas e emissão de documentos ocorrem em sistemas distintos",
                "alto", "Média de 7-10 dias para conclusão vs benchmark 2-3 dias", "Processos"),
            new AchadoDiagnostico("Falta de indicadores consolidados",
                "Decisões gerenciais tomadas sem base estruturada em dados",
                "alto", "Não existem dashboards estratégicos consolidados", "Governança"),
            new AchadoDiagnostico("Alta rotatividade docente",
                "Taxa de turnover 18% ao ano, acima da média",
                "alto", "Perda de 8-10 professores por semestre", "Gestão de Pessoas")
        ));

        d.setPontosFortesIdentificados(List.of(
            "Corpo docente qualificado (78% com mestrado)",
            "Infraestrutura tecnológica em expansão",
            "Forte reputação acadêmica"
        ));

        d.setAreaCritica(List.of(
            "Processos Administrativos",
            "Governança de Dados",
            "Comunicação Estratégica"
        ));

        d.setOportunidadesIdentificadas(List.of(
            "Implementação de sistema integrado (ERP)",
            "Criação de escritório de projetos (PMO)",
            "Dashboard executivo com KPIs"
        ));

        return d;
    }

    private Diagnostico criarDiagnosticoProcessos() {
        Diagnostico d = new Diagnostico();
        d.setResumoExecutivo(
            "O processo de matrícula apresenta 12 etapas com 3 gargalos críticos. " +
            "Tempo total: 7-10 dias vs benchmark 2-3 dias."
        );

        d.setAchados(List.of(
            new AchadoDiagnostico("Gargalo: Aprovação de Pré-requisitos Manual",
                "Realizada manualmente por coordenador sem automatização",
                "alto", "Backlog de 150-200 alunos; tempo médio 2.5 dias", "Processos"),
            new AchadoDiagnostico("Gargalo: Validação Descentralizada",
                "Múltiplos sistemas verificam documentação sem integração",
                "alto", "Retrabalho frequente; alunos visitam 3-4 setores", "Integração")
        ));

        d.setPontosFortesIdentificados(List.of(
            "Comunidade aceitável do sistema de pré-seleção",
            "Documentação clara de pré-requisitos"
        ));

        d.setAreaCritica(List.of(
            "Automatização de Processos",
            "Integração de Sistemas"
        ));

        d.setOportunidadesIdentificadas(List.of(
            "Automatizar validação de pré-requisitos",
            "Integrar sistemas em plataforma única",
            "Implementar assinatura digital"
        ));

        return d;
    }

    private List<SugestoMelhoria> criarSugestoesEstrategicas() {
        List<SugestoMelhoria> sugestoes = new ArrayList<>();
        sugestoes.add(new SugestoMelhoria(1L, "Implementar Sistema ERP Integrado",
            "Consolidar todos módulos administrativos em sistema integrado",
            "alta", "transformador", 85, 90, 180, "Pró-Reitoria de Administração", "aprovada"));
        sugestoes.add(new SugestoMelhoria(2L, "Criar Dashboard de Indicadores",
            "Plataforma BI com KPIs em tempo real",
            "alta", "transformador", 70, 88, 90, "TI", "em_analise"));
        sugestoes.add(new SugestoMelhoria(3L, "Programa de Desenvolvimento de Líderes",
            "Programa de capacitação para gestores",
            "alta", "significativo", 50, 75, 60, "CRH", "proposta"));
        return sugestoes;
    }

    private List<SugestoMelhoria> criarSugestoesProcessos() {
        List<SugestoMelhoria> sugestoes = new ArrayList<>();
        sugestoes.add(new SugestoMelhoria(101L, "Automatizar Validação de Pré-Requisitos",
            "Mecanismo automático que valida histórico acadêmico",
            "alta", "transformador", 45, 85, 45, "TI", "proposta"));
        sugestoes.add(new SugestoMelhoria(102L, "Integrar Plataforma de Validação",
            "Centralizar validação em portal único",
            "alta", "transformador", 60, 82, 60, "TI", "aprovada"));
        return sugestoes;
    }

    private ClassificacaoMaturidade criarMaturidadeEstrategica() {
        ClassificacaoMaturidade m = new ClassificacaoMaturidade();
        m.setScore(68);
        m.setNiveau("em_desenvolvimento");
        m.setComparativoSetor(62);
        
        TrajetoriaMelhoria t = new TrajetoriaMelhoria();
        t.setDataAtual(LocalDate.of(2026, 3, 20));
        t.setScoreAtual(68);
        t.setScoreProjetado12Meses(78);
        t.setCaminhoMelhor(List.of(
            "Q2 2026: Implementar ERP (+8 pontos)",
            "Q3 2026: Dashboard estratégico (+6 pontos)",
            "Q4 2026: Revisão plano estratégico (+4 pontos)"
        ));
        m.setTrajetoriaMelhoria(t);
        return m;
    }

    private ClassificacaoMaturidade criarMaturidadeProcessos() {
        ClassificacaoMaturidade m = new ClassificacaoMaturidade();
        m.setScore(52);
        m.setNiveau("iniciante");
        m.setComparativoSetor(55);
        
        TrajetoriaMelhoria t = new TrajetoriaMelhoria();
        t.setDataAtual(LocalDate.of(2026, 3, 18));
        t.setScoreAtual(52);
        t.setScoreProjetado12Meses(78);
        t.setCaminhoMelhor(List.of(
            "Mês 1-2: Automatizar validação (+12 pontos)",
            "Mês 3-4: Integrar plataforma (+14 pontos)"
        ));
        m.setTrajetoriaMelhoria(t);
        return m;
    }
}
