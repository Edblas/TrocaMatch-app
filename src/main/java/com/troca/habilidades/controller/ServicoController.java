package com.troca.habilidades.controller;

import com.troca.habilidades.entity.Servico;
import com.troca.habilidades.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @PostMapping("/criar")
    public Servico criarServico(@RequestBody Servico servico) {
        return servicoService.criarServico(servico);
    }

    @GetMapping
    public List<Servico> listarServicos() {
        return servicoService.listarServicos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Servico> listarServicosPorUsuario(@PathVariable Long usuarioId) {
        return servicoService.listarServicosPorUsuario(usuarioId);
    }
}
