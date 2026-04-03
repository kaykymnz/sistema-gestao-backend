package com.novahorizonte.backend.chat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.novahorizonte.backend.model.Funcionario;
import com.novahorizonte.backend.model.ProjetoPlanejamento;
import com.novahorizonte.backend.model.Relatorio;
import com.novahorizonte.backend.service.FuncionarioService;
import com.novahorizonte.backend.service.PlanejamentoProjetoService;
import com.novahorizonte.backend.service.RelatorioService;

@Service
public class ChatContextService {

    private final RelatorioService relatorioService;
    private final FuncionarioService funcionarioService;
    private final PlanejamentoProjetoService planejamentoProjetoService;

    public ChatContextService(
        RelatorioService relatorioService,
        FuncionarioService funcionarioService,
        PlanejamentoProjetoService planejamentoProjetoService
    ) {
        this.relatorioService = relatorioService;
        this.funcionarioService = funcionarioService;
        this.planejamentoProjetoService = planejamentoProjetoService;
    }

    public ChatContext buildContext() {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        List<ProjetoPlanejamento> projetos = planejamentoProjetoService.listarProjetos();

        String dashboardResumo = "Relatórios ativos: " + relatorios.size()
            + ", Funcionários monitorados: " + funcionarios.size()
            + ", Projetos estratégicos: " + projetos.size();

        List<String> processosCriticos = relatorios.stream()
            .map(Relatorio::getDiagnostico)
            .filter(diagnostico -> diagnostico != null && diagnostico.getAreaCritica() != null)
            .flatMap(diagnostico -> diagnostico.getAreaCritica().stream())
            .distinct()
            .limit(6)
            .collect(Collectors.toList());

        List<String> indicadoresPessoas = funcionarios.stream()
            .map(funcionario -> funcionario.getDepartamento() + " - " + funcionario.getFuncao())
            .limit(6)
            .collect(Collectors.toList());

        List<String> planejamentoAtivo = projetos.stream()
            .map(projeto -> projeto.getTitulo() + " (" + projeto.getStatus() + ")")
            .limit(6)
            .collect(Collectors.toList());

        return new ChatContext(
            dashboardResumo,
            processosCriticos,
            indicadoresPessoas,
            planejamentoAtivo
        );
    }
}
