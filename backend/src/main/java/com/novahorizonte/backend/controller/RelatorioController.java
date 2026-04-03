package com.novahorizonte.backend.controller;

import com.novahorizonte.backend.model.Relatorio;
import com.novahorizonte.backend.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para Relatórios Inteligentes
 * Endpoints: GET (listar, buscar), POST (criar), PUT (atualizar), DELETE (remover)
 */
@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    /**
     * GET /api/relatorios
     * Retorna lista de todos os relatórios
     */
    @GetMapping
    public ResponseEntity<List<Relatorio>> listarTodos() {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        return ResponseEntity.ok(relatorios);
    }

    /**
     * GET /api/relatorios/{id}
     * Retorna um relatório específico por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * GET /api/relatorios/tipo/{tipo}
     * Retorna relatórios filtrados por tipo
     * Tipos: "diagnostico", "analise", "predicao"
     */
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Relatorio>> buscarPorTipo(@PathVariable String tipo) {
        List<Relatorio> relatorios = relatorioService.buscarPorTipo(tipo);
        
        if (relatorios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(relatorios);
    }

    /**
     * POST /api/relatorios
     * Cria um novo relatório
     * Body: { titulo, descricao, tipo, diagnostico, sugestoes, classificacaoMaturidade }
     */
    @PostMapping
    public ResponseEntity<Relatorio> criarRelatorio(@RequestBody Relatorio novoRelatorio) {
        try {
            // Validação básica
            if (novoRelatorio.getTitulo() == null || novoRelatorio.getTitulo().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Relatorio relatorioGravado = relatorioService.criarRelatorio(novoRelatorio);
            return ResponseEntity.status(HttpStatus.CREATED).body(relatorioGravado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * PUT /api/relatorios/{id}
     * Atualiza um relatório existente
     * Body: { titulo, descricao, status, diagnostico, sugestoes }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Relatorio> atualizarRelatorio(
        @PathVariable Long id,
        @RequestBody Relatorio relatorioAtualizado) {
        
        return relatorioService.atualizarRelatorio(id, relatorioAtualizado)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * DELETE /api/relatorios/{id}
     * Remove um relatório por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRelatorio(@PathVariable Long id) {
        boolean deletado = relatorioService.deletarRelatorio(id);
        
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * POST /api/relatorios/gerar
     * Endpoint para gerar relatório novo com parâmetros
     * Body: { dataInicio, dataFim, filtro, incluirComparativo }
     * Simula processamento por IA
     */
    @PostMapping("/gerar")
    public ResponseEntity<Relatorio> gerarRelatorio(@RequestBody RelatorioParametros parametros) {
        try {
            // Validação básica
            if (parametros.getDataInicio() == null || parametros.getDataFim() == null) {
                return ResponseEntity.badRequest().build();
            }

            // Criar relatório com base nos parâmetros
            Relatorio novoRelatorio = criarRelatorioAPartirParametros(parametros);
            Relatorio relatorioGravado = relatorioService.criarRelatorio(novoRelatorio);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(relatorioGravado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Cria um relatório com base nos parâmetros de geração
     */
    private Relatorio criarRelatorioAPartirParametros(RelatorioParametros parametros) {
        Relatorio r = new Relatorio();
        r.setTitulo("Relatório Diagnóstico - " + parametros.getDataFim());
        r.setDescricao("Análise diagnóstica do período de " + parametros.getDataInicio() + 
                      " a " + parametros.getDataFim());
        r.setTipo("diagnostico");
        r.setConfiabilidade(85);
        r.setResponsavel("Sistema de IA");
        r.setStatus("rascunho");
        
        // TODO: Popuar com dados baseados em parâmetros quando backend tiver dados reais
        
        return r;
    }

    /**
     * Classe auxiliar para receber parâmetros de geração
     */
    public static class RelatorioParametros {
        private String dataInicio;
        private String dataFim;
        private String filtro;
        private Boolean incluirComparativo;

        public String getDataInicio() { return dataInicio; }
        public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

        public String getDataFim() { return dataFim; }
        public void setDataFim(String dataFim) { this.dataFim = dataFim; }

        public String getFiltro() { return filtro; }
        public void setFiltro(String filtro) { this.filtro = filtro; }

        public Boolean getIncluirComparativo() { return incluirComparativo; }
        public void setIncluirComparativo(Boolean incluirComparativo) { 
            this.incluirComparativo = incluirComparativo; 
        }
    }
}
