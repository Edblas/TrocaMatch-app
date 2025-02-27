package com.troca.habilidades.controller;

import com.troca.habilidades.entity.Troca;
import com.troca.habilidades.service.TrocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trocas")
public class TrocaController {

    @Autowired
    private TrocaService trocaService;

    @PostMapping("/registrar")
    public Troca registrarTroca(@RequestBody Troca troca) {
        return trocaService.registrarTroca(troca);
    }

    @GetMapping
    public List<Troca> listarTrocas() {
        return trocaService.listarTrocas();
    }
}
