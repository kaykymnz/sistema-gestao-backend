package com.novahorizonte.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novahorizonte.backend.dto.PlanoAcao5W2HDto;
import com.novahorizonte.backend.model.ProjetoPlanejamento;
import com.novahorizonte.backend.service.PlanejamentoProjetoService;

@RestController
@RequestMapping("/api/planejamento")
public class PlanejamentoProjetoController {

    @Autowired
    private PlanejamentoProjetoService planejamentoProjetoService;

    @GetMapping("/projetos")
    public ResponseEntity<List<ProjetoPlanejamento>> listarProjetos() {
        return ResponseEntity.ok(planejamentoProjetoService.listarProjetos());
    }

    @GetMapping("/projetos/{id}")
    public ResponseEntity<ProjetoPlanejamento> buscarPorId(@PathVariable Long id) {
        ProjetoPlanejamento projeto = planejamentoProjetoService.buscarPorId(id);

        if (projeto != null) {
            return ResponseEntity.ok(projeto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/5w2h")
    public ResponseEntity<List<PlanoAcao5W2HDto>> listarPlanos5W2H() {
        return ResponseEntity.ok(planejamentoProjetoService.listarPlanos5W2H());
    }
}
